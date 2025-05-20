package com.devu.reservation.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
	private String activityId;
    private String cruiseId;
    private String name;
    private String type;
    private String location;
    private double price;
    //private int availability;
}
