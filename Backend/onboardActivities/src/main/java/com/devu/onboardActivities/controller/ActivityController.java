package com.devu.onboardActivities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devu.onboardActivities.enitities.Activity;
import com.devu.onboardActivities.service.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	// Endpoint to retrieve all activities
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
    
    @GetMapping("/cruise/{cruiseId}")
    ResponseEntity<List<Activity>> getActivitiesByCruiseId(@PathVariable String cruiseId){
    	List<Activity> activities=activityService.getActivitiesByCruiseId(cruiseId);
    	return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // Endpoint to retrieve a single activity by ID
    @GetMapping("/{activityId}")
    ResponseEntity <Activity> getActivityByActivityId(@PathVariable String activityId){
    	Activity activity=activityService.getActivityByActivityId(activityId);
    	return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    // Endpoint to add a new activity
    @PostMapping
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
        Activity newActivity = activityService.addActivity(activity);
        return new ResponseEntity<>(newActivity, HttpStatus.CREATED);
    }

    // Endpoint to update an existing activity
    @PutMapping("/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable String activityId, @RequestBody Activity activity) {
        Activity updatedActivity = activityService.updateActivity(activityId, activity);
        if (updatedActivity != null) {
            return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete an activity
    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable String activityId) {
        activityService.deleteActivity(activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    


}
