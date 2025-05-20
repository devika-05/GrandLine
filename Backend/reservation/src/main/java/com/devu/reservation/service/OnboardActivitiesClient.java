package com.devu.reservation.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devu.reservation.entities.Activity;

@FeignClient(name="Onboard-service")
public interface OnboardActivitiesClient {
	
	@GetMapping("/activities/cruise/{cruiseId}")
	List<Activity> getActivitiesByCruiseId(@PathVariable String cruiseId);

}
