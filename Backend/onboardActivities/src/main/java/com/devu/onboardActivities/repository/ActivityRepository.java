package com.devu.onboardActivities.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devu.onboardActivities.enitities.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {

	List<Activity> findByCruiseId(String cruiseId);

	Activity findByActivityId(String activityId);

	void deleteByActivityId(String activityId);

}
