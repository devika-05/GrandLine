package com.devu.reservation.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devu.cruiseBook.entities.Passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Reservation")
public class Reservation {
	@Id
	private String id;
    private String reservationId;
    private String cruiseId;
//    private String status;
    private double amount;
    private double totalPaid;
    private List<Passenger> passengers;
    private List<Activity> activities = new ArrayList<>();
}
