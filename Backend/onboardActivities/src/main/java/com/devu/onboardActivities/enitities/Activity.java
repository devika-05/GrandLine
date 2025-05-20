package com.devu.onboardActivities.enitities;

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
@Document(collection="Activity")
public class Activity {

	@Id
	private String id;
    private String activityId;
    private String cruiseId;
    private String name;
    private String type;
    private String location;
    private double price;
    //private int availability;
}
