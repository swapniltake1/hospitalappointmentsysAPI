package com.hospital.appointment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import com.hospital.appointment.Entities.Appointment;
import com.hospital.appointment.Entities.AppointmentRequest;
import com.hospital.appointment.service.CsvDataProvider;
import jakarta.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	Appointment appointmentcheck;

	    private final Map<String, List<Appointment>> appointmentsByDate = new HashMap<>();
	    
	    @GetMapping("/home")
	    public ModelAndView home() {
	    	ModelAndView modelAndView = new ModelAndView("home");
	        return modelAndView;
		}

	    @PostMapping("/book")
	    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request, HttpSession
	    		 session, ModelAndView modelAndView, Model model) {
	        // Validate input
	        validateInput(request);

	        // Check if the appointment already exists
	        if (isAppointmentBooked(request.getDoctorName(), request.getAppointmentDate())) {
	            throw new ResponseStatusException(HttpStatus.CONFLICT, "Appointment already booked for this doctor on the given date");
	        }

	        // Create a new appointment
	        Appointment appointment = new Appointment(request.getHospitalName(), request.getDoctorName(), request.getAppointmentDate(),request.getAppointmentTime(), 0);

	        // Store the appointment in the session
	       
	        storeAppointment(appointment);
	        
	     //   session.setAttribute("appointment", appointment);
            
	        model.addAttribute("successMessage", "Form submitted successfully!");
	        
	        
	        return ResponseEntity.ok("Appointment booked successfully");
	        
	    }

	    @SuppressWarnings({ "unchecked", "unused" })
		@GetMapping("/all")
	    public ResponseEntity<List<Appointment>> getAllAppointments() {
	        List<Appointment> allAppointments = new ArrayList<>();
	        if (allAppointments==null) {
				return (ResponseEntity<List<Appointment>>) ResponseEntity.notFound();
			}
	        for (List<Appointment> appointments : appointmentsByDate.values()) {
	            allAppointments.addAll(appointments);
	        }
	        return ResponseEntity.ok(allAppointments);
	    }

	    private void validateInput(AppointmentRequest request) {
	        // Trim and validate hospital name
	        @SuppressWarnings("deprecation")
			String hospitalName = StringUtils.trimWhitespace(request.getHospitalName());
	        if (!CsvDataProvider.hospitalExists(hospitalName)) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid hospital name");
	        }

	        // Trim and validate doctor name
	        @SuppressWarnings("deprecation")
			String doctorName = StringUtils.trimWhitespace(request.getDoctorName());
	        if (!CsvDataProvider.doctorExists(doctorName)) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid doctor name");
	        }

	        // Validate appointment date
	        if (!isValidAppointmentDate(request.getAppointmentDate())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid appointment date");
	        }
	    }

	    private boolean isAppointmentBooked(String doctorName, LocalDate localDate) {
	        @SuppressWarnings("unlikely-arg-type")
			List<Appointment> appointments = appointmentsByDate.get(localDate);
	        if (appointments != null) {
	            for (Appointment appointment : appointments) {
	                if (appointment.getDoctorName().equalsIgnoreCase(doctorName)) {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

	    private void storeAppointment(Appointment appointment) {
	        String appointmentDate = appointment.getAppointmentDate().toString();
	        List<Appointment> appointments = appointmentsByDate.computeIfAbsent(appointmentDate, k -> new ArrayList<>());
	        appointments.add(new Appointment(appointment.getHospitalName(), appointment.getDoctorName(), appointment.getPatientName(), appointment.getAppointmentDate(), appointment.getAppointmentTime(), appointment.getAppointmenthour()));
	    }



         // for checking appointment not book on saturday and sundays 
	    private boolean isValidAppointmentDate(LocalDate localDate) {
	        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
	        return !(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
	    }

	}

