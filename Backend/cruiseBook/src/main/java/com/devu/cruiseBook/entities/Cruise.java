package com.devu.cruiseBook.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@NoArgsConstructor
@AllArgsConstructor

@Document(collection="Cruise")
public class Cruise {
	
	
	private String id;
	private byte[] image;
	private String cruiseId;
    private String name;
    private String departurePort;
    private String destination;
    private LocalDate departureDate;
    private double price;
    private int availableRooms;
	private int maxCapacity=availableRooms;
	
	public CruiseDto getCruiseDto() {
		CruiseDto cruiseDto = new CruiseDto();
		cruiseDto.setId(id);
		cruiseDto.setCruiseId(cruiseId);
		cruiseDto.setName(name);
		cruiseDto.setDeparturePort(departurePort);
		cruiseDto.setDestination(destination);
		cruiseDto.setDepartureDate(departureDate);
		cruiseDto.setPrice(price);
		cruiseDto.setAvailableRooms(availableRooms);
		cruiseDto.setReturnedImage(image);
		return cruiseDto;
	}
}
