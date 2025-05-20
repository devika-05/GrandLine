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
public class ReservationRequest {
	 private String email;
	 private String cruiseId;
	 private double cruisePrice;
	 private List<Passenger> passengers;
}
