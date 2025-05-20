package com.devu.cruiseBook.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devu.cruiseBook.entities.Booking;
import com.devu.cruiseBook.entities.BookingRequest;
import com.devu.cruiseBook.entities.Cruise;
import com.devu.cruiseBook.entities.Reservation;
import com.devu.cruiseBook.entities.ReservationRequest;
import com.devu.cruiseBook.repository.BookingRepository;
import com.devu.cruiseBook.repository.CruiseRepository;

@Service
public class BookingService {

	@Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CruiseRepository cruiseRepository;
    
    @Autowired
    private ReservationApplicationClient reservationApplicationClient;

    public Booking bookCruise(BookingRequest bookingRequest) {
        int count= bookingRequest.getPassengers().size();
        //Call the Reservation Management service using Feign client
		Cruise cruise = cruiseRepository.findByCruiseId(bookingRequest.getCruiseId());
        
        // Check if the cruise exists and if there are available rooms
        if (cruise != null && cruise.getAvailableRooms() > 0) {
            // Reduce available rooms count
        	double d= count*cruise.getPrice();
            //cruise.setAvailableRooms(cruise.getAvailableRooms() - count);
            cruiseRepository.save(cruise);
            bookingRequest.setCruisePrice(d);
            Reservation reservation= reservationApplicationClient.createReservation(bookingRequest);
            Booking booking=new Booking();
            booking.setCruiseId(reservation.getCruiseId());
            booking.setStatus(reservation.getStatus());
            booking.setAmount(reservation.getAmount()+d);
            booking.setBookingId(UUID.randomUUID().toString());
            booking.setEmail(bookingRequest.getEmail());
            booking.setPassengers(reservation.getPassengers());
            booking.setActivities(reservation.getActivities());
            booking.setStatus("Pending Payment");
            booking.setCruise(cruise);
            bookingRepository.save(booking);
            return booking;}
        else {
        	 throw new RuntimeException("Booking failed: Either cruise not found or no available rooms.");
        }
    }
    
    public Cruise getCruiseById(String id) {
    	return cruiseRepository.findById(id).get();  	
    }

    public ResponseEntity<String> cancelBooking(ReservationRequest bookingRequest) {
    	int count= bookingRequest.getPassengers().size();
    	Cruise cruise = cruiseRepository.findByCruiseId(bookingRequest.getCruiseId());
        if (cruise != null) {
            // Check if there are available rooms
            if (cruise.getAvailableRooms() < cruise.getMaxCapacity()) {
                // Increment the available rooms count
                cruise.setAvailableRooms(cruise.getAvailableRooms() + count);
                cruiseRepository.save(cruise);
                
                // Call the Reservation Management service to delete the reservation
                return reservationApplicationClient.deleteReservation(bookingRequest);
            } else {
                // No available rooms to cancel the booking
                throw new RuntimeException("Cancel failed: No available rooms to cancel the booking.");
            }
        } else {
            // Cruise not found
            throw new RuntimeException("Cancel failed: Cruise not found with ID: " + bookingRequest.getCruiseId());
        }
//		if (cruise != null) {
//            // Update the available rooms count when a booking is canceled
//            int availableRooms = cruise.getAvailableRooms();
//            if (availableRooms < cruise.getMaxCapacity()) { 
//                cruise.setAvailableRooms(availableRooms + 1);
//                cruiseRepository.save(cruise);
//                return reservationApplicationClient.deleteReservation(bookingRequest);
//            }
//		}
//		else {
//			throw new RuntimeException("Cancel failed: Either cruise not found or no available rooms.");
//		      
//		}
		
	}


	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Booking getBookingByBookingId(String bookingId) {
		Booking booking= bookingRepository.findByBookingId(bookingId);
		return booking;
	}

	@Transactional
	public Booking updateBookingByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		Booking booking =bookingRepository.findByBookingId(bookingId);
		booking.setStatus("Payment successful and Booking confirmed");
		bookingRepository.save(booking);
		int count= booking.getPassengers().size();
		Cruise cruise = cruiseRepository.findByCruiseId(booking.getCruiseId());
		cruise.setAvailableRooms(cruise.getAvailableRooms() - count);
        cruiseRepository.save(cruise);
		return booking;
	}

	public List<Booking> getAllBookingByEmail(String email) {
		return bookingRepository.findByEmail(email);
	}
}
