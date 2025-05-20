package com.devu.cruiseBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devu.cruiseBook.entities.Booking;
import com.devu.cruiseBook.entities.BookingRequest;
import com.devu.cruiseBook.entities.Cruise;
import com.devu.cruiseBook.entities.CruiseDto;
import com.devu.cruiseBook.entities.Passenger;
import com.devu.cruiseBook.entities.ReservationRequest;
import com.devu.cruiseBook.repository.CruiseRepository;
import com.devu.cruiseBook.services.BookingService;


@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
    private BookingService bookingService;
	
	@Autowired
	CruiseRepository cruiseRepository;
	
	
    @PostMapping
    public ResponseEntity<Booking> bookCruise(@RequestBody BookingRequest bookingRequest) {
    	Booking booking = bookingService.bookCruise(bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelBooking(@RequestBody ReservationRequest bookingRequest) {
       return bookingService.cancelBooking(bookingRequest);
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("{bookingId}")
    public ResponseEntity<?> updateBookingByBookingId(@PathVariable String bookingId){
    	Booking booking=bookingService.updateBookingByBookingId(bookingId);
    	if(booking!=null) {
    		return new ResponseEntity<>(booking, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping
    public List<Booking> getAllBookings(){
    	return bookingService.getAllBookings();
    }
    
    @GetMapping("{bookingId}")
    public ResponseEntity<Booking> getBookingByBookingId(@PathVariable String bookingId){
    	Booking booking=bookingService.getBookingByBookingId(bookingId);
    	if(booking!=null) {
    		return new ResponseEntity<>(booking, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping("/cruise/{id}")
    public ResponseEntity<Cruise> getCruiseById(@PathVariable String id) {
        Cruise cruise = bookingService.getCruiseById(id);
        if (cruise != null) {
            return new ResponseEntity<>(cruise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("email/{email}")
    public ResponseEntity<List<Booking>> getAllBookingByEmail(@PathVariable String email){
    	List<Booking> bookings= bookingService.getAllBookingByEmail(email);
    	if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bookings);
        }
    }
}
