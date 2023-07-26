package com.hospital.appointment.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

public class CsvDataProvider {
	

    public static boolean hospitalExists(String hospitalName) {
    	 try {
             // Get the CSV file from the classpath resource
             ClassPathResource resource = new ClassPathResource("Hospital.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

             String line;
             while ((line = reader.readLine()) != null) {
                 // Split the CSV line by comma (assuming CSV file has hospital names in the first column)
                 String[] data = line.split(",");

                 // Check if the hospital name matches
                 if (data.length > 0 && data[0].equalsIgnoreCase(hospitalName)) {
                     return true; // If Hospital exists
                 }
             }

             reader.close();
         } catch (IOException e) {
             e.printStackTrace();
         }

         return false; //If Hospital does not exist
     }
    

    public static boolean doctorExists(String doctorName) {
       
        try {
            // Get the CSV file from the classpath resource
            ClassPathResource resource = new ClassPathResource("Doctor.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] data = line.split(",");

                // Check if the doctor name matches
                if (data.length > 0 && data[0].equalsIgnoreCase(doctorName)) {
                    return true; //IF Doctor exists
                }
            }

            // Close the reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; //if Doctor does not exist
    }
    }

