package com.devu.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devu.reservation.entities.Reservation;
import com.devu.reservation.entities.ReservationRequest;
import com.devu.reservation.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
    private ReservationService reservationService;
	
	@GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationService.createReservation(reservationRequest);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation updatedReservation = reservationService.updateReservation(reservationRequest);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Reservation> deleteReservation(@RequestBody ReservationRequest reservationRequest) {
    	Reservation reservation=reservationService.deleteReservation(reservationRequest);
        return new ResponseEntity<>(reservation,HttpStatus.NO_CONTENT);
    }
}
