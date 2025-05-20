package com.devu.reservation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devu.reservation.entities.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

	//Reservation findByReservationId(String reservationId);

	//void deleteByReservationId(String reservationId);

	//Reservation findByUserId(String userId);

	//void deleteByCruiseId(String cruiseId);

	Reservation findByCruiseId(String cruiseId);

	//Reservation findByCruiseId(String cruiseId);

}
