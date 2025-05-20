package com.devu.cruiseBook.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
	private String id;
    private String reservationId;
    private String cruiseId;
    private String status;
    private double amount;
    private List<Passenger> passengers;
    private List<Activity> activities = new ArrayList<>();
}
