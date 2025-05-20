package com.devu.cruiseBook.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CruiseDto {
	
	@Id
	private String id;
	private MultipartFile image;
	private String cruiseId;
    private String name;
    private String departurePort;
    private String destination;
    private LocalDate departureDate;
    private double price;
    private int availableRooms;
    private  byte[] returnedImage;
    
}
