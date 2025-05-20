package com.devu.cruiseBook.entities;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Booking")
public class Booking {
	
	@Id
	private String id;
	private String bookingId;
    private String email;
    private double amount;
    private String cruiseId;
    private String status;
    private Cruise cruise;
    private List<Passenger> passengers;
    private List<Activity> activities;
    
    

}
