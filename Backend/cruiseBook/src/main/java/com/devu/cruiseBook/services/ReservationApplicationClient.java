package com.devu.cruiseBook.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.devu.cruiseBook.entities.Booking;
import com.devu.cruiseBook.entities.BookingRequest;
import com.devu.cruiseBook.entities.Reservation;
import com.devu.cruiseBook.entities.ReservationRequest;

@FeignClient(name="reservation-service")
public interface ReservationApplicationClient {

	//ResponseEntity<String> createReservation(BookingRequest bookingRequest);
	
	@PostMapping("/reservations")
	public Reservation createReservation(BookingRequest bookingRequest);
	
	@DeleteMapping("/reservations")
	public ResponseEntity<String> deleteReservation(ReservationRequest bookingRequest);

	//public ResponseEntity<String> createReservation(BookingRequest bookingRequest);

}
