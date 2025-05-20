package com.devu.onboardActivities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devu.onboardActivities.enitities.Activity;
import com.devu.onboardActivities.repository.ActivityRepository;
import com.google.common.base.Optional;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	public List<Activity> getAllActivities() {
		return activityRepository.findAll();
	}
	
	public List<Activity> getActivitiesByCruiseId(String cruiseId){
		return activityRepository.findByCruiseId(cruiseId);
	}
	
	public Activity getActivityByActivityId(String activityId) {
        Activity activity = activityRepository.findByActivityId(activityId);
        return activity;
    }
	
	public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }
	
	public Activity updateActivity(String activityId, Activity activity) {
		Activity existingActivity = (Activity) activityRepository.findByActivityId(activityId);
		if(existingActivity !=null) {
			existingActivity.setCruiseId(activity.getCruiseId());
			existingActivity.setLocation(activity.getLocation());
			existingActivity.setName(activity.getName());
			existingActivity.setPrice(activity.getPrice());
			existingActivity.setType(activity.getType());
			return activityRepository.save(existingActivity);
		}
		return null;
		
    }
	
	public void deleteActivity(String activityId) {
		System.out.println("THE ACTIVITY HAS BEEN DELETED");
        activityRepository.deleteByActivityId(activityId);
    }
	
//	public List<Activity> getActivitiesForCruise(String cruiseId) {
//        return activityRepository.findByCruiseId(cruiseId);
//    }

}
