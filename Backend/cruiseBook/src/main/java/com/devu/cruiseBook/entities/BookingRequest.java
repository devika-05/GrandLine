package com.devu.cruiseBook.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
	private double cruisePrice;
	 private String email;
	 private String cruiseId;
	 private List<Passenger> passengers;
}
