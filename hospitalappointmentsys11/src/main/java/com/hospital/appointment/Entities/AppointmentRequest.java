package com.hospital.appointment.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {
	private String hospitalName;
    private String doctorName;
    private String patientName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    
	public AppointmentRequest(String hospitalName, String doctorName, String patientName, LocalDate appointmentDate,
			LocalTime appointmentTime) {
		super();
		this.hospitalName = hospitalName;
		this.doctorName = doctorName;
		this.setPatientName(patientName);
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
	}

    // Generate getters and setters
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


    
}
