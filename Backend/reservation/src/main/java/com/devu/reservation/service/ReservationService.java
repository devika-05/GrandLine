package com.devu.reservation.service;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devu.reservation.entities.Activity;
import com.devu.reservation.entities.Reservation;
import com.devu.reservation.entities.ReservationRequest;
import com.devu.reservation.entities.User;
import com.devu.reservation.repository.ReservationRepository;
import com.devu.reservation.repository.UserRepository;
@Service
public class ReservationService {
	
	 	@Autowired
	    private ReservationRepository reservationRepository;
	 	
	 	@Autowired
	 	private OnboardActivitiesClient onboardActivitiesClient;
	 	
	 	@Autowired
	    private UserRepository userRepository;
	 	
//	 	@Autowired
//	 	private OnboardActivitiesClient onboardActivitiesClient;
//	 	
	    public List<Reservation> getAllReservations() {
	    	List<Reservation> reservations = reservationRepository.findAll();
	    	return reservations;
	    }

	    @Transactional
	    public Reservation createReservation(ReservationRequest reservationRequest) {
	        // Check if the user ID is provided
	        if (reservationRequest.getCruiseId() == null) {
	            throw new IllegalArgumentException("User ID is required");
	        }

	        // Fetch the user by ID
	        User user = userRepository.findByEmail(reservationRequest.getEmail());
	        if (user == null) {
	            throw new RuntimeException("User not found with ID: " + reservationRequest.getEmail());
	        }

	        // Create a new reservation
	        Reservation reservation = new Reservation();
	        reservation.setReservationId(UUID.randomUUID().toString());
	        reservation.setCruiseId(reservationRequest.getCruiseId());
//	        reservation.setStatus("pending payment"); // Assuming it's confirmed upon creation
	        List<Activity> activities=onboardActivitiesClient.getActivitiesByCruiseId(reservationRequest.getCruiseId());
	        double totalActivityPrice = 0.0;
	        for (Activity activity : activities) {
	            totalActivityPrice += activity.getPrice();
	        }
	        reservation.setActivities(activities);
	        reservation.setAmount(totalActivityPrice);
	        reservation.setTotalPaid(totalActivityPrice+reservationRequest.getCruisePrice());
	        reservation.setPassengers(reservationRequest.getPassengers());
	        // Add the reservation to the user's list of reservations
	        user.getReservations().add(reservation);
	        reservationRepository.save(reservation);
	        // Save the updated user (which includes the new reservation)
	        userRepository.save(user);

	        return reservation;
	    }
	    @Transactional
	    public Reservation updateReservation(ReservationRequest reservationRequest) {
	        // Fetch the existing reservation from the database
	        Reservation existingReservation = reservationRepository.findByCruiseId(reservationRequest.getCruiseId());
	        if (existingReservation == null) {
	            throw new RuntimeException("Reservation not found with ID: " + reservationRequest.getCruiseId());
	        }

	        // You can update other fields as needed

	        // Save the updated reservation
	        return reservationRepository.save(existingReservation);
	    }
	    
	    @Transactional
	    public Reservation deleteReservation(ReservationRequest reservationRequest) {	    	
	        if (reservationRequest.getCruiseId() == null) {
	            throw new IllegalArgumentException("User ID is required");
	        }

	        // Fetch the user by ID
	        User user = userRepository.findByEmail(reservationRequest.getEmail());
	        if (user == null) {
	            throw new RuntimeException("User not found with ID: " + reservationRequest.getEmail());
	        }
	        
	        // Find the reservation by ID
	        Reservation reservation = user.getReservations().stream()
                    .filter(res -> res.getCruiseId().equals(reservationRequest.getCruiseId()))
                    .findFirst()
                    .orElse(null);
			if (reservation == null) {
			throw new RuntimeException("Reservation not found for cruise with ID: " + reservationRequest.getCruiseId());
			}
			
			// Remove the reservation from the user's list
			user.getReservations().remove(reservation);
			// Save the updated user
			userRepository.save(user);
			
			// Delete the reservation from the repository
			reservationRepository.delete(reservation);
			
			return reservation;
	        

	    }
}
