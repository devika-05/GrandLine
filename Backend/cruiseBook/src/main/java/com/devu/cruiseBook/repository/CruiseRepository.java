package com.devu.cruiseBook.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devu.cruiseBook.entities.Cruise;

@Repository
public interface CruiseRepository extends MongoRepository<Cruise, String> {

	Cruise findByCruiseId(String cruiseId);

	String deleteByCruiseId(String cruiseId);
}
