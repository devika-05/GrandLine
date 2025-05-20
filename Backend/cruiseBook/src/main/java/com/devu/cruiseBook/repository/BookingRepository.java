package com.devu.cruiseBook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devu.cruiseBook.entities.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String>{

	Booking findByBookingId(String bookingId);

	List<Booking> findByEmail(String email);

}
