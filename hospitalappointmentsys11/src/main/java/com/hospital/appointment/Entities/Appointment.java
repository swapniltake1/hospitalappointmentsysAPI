package com.hospital.appointment.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class Appointment {
	
	private String hospitalName;
    private String doctorName;
    private String patientName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private int appointmenthour;
    
    
    
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Appointment(String string, String string2, String string3, LocalDate localDate, LocalTime localTime, int int1) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Appointment(String hospitalName, String doctorName, LocalDate appointmentDate, LocalTime appointmentTime, int appointmenthour) {
		super();
		this.hospitalName = hospitalName;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.appointmenthour = appointmenthour;
	}


	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public int getAppointmenthour() {
		return appointmenthour;
	}


	public void setAppointmenthour(int appointmenthour) {
		this.appointmenthour = appointmenthour;
	}

 
}
