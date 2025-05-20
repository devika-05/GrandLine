package com.devu.cruiseBook.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devu.cruiseBook.entities.Cruise;
import com.devu.cruiseBook.entities.CruiseDto;
import com.devu.cruiseBook.services.CruiseService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/cruises")
public class CruiseController {
	 @Autowired
	    private CruiseService cruiseService;

	    @GetMapping("/all")
	    public ResponseEntity<List<CruiseDto>> getAllCruises() {
	    	List<CruiseDto> cruiseList=cruiseService.getAllCruises();
	        return ResponseEntity.ok(cruiseList);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CruiseDto> getCruiseById(@PathVariable String id) {
	        CruiseDto cruise = cruiseService.getCruiseById(id);
	        if (cruise != null) {
	            return new ResponseEntity<>(cruise, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @GetMapping("cId/{cruiseId}")
	    public ResponseEntity<Cruise> getCruiseByCruiseId(@PathVariable String cruiseId) {
	        Cruise cruise = cruiseService.getCruiseByCruiseId(cruiseId);
	        if (cruise != null) {
	            return new ResponseEntity<>(cruise, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/add")
	    public ResponseEntity<?> addCruise(@ModelAttribute  CruiseDto cruiseDto) throws IOException {	    	
	    	boolean  success= cruiseService.addCruise(cruiseDto);
	    	if(success) {
	    		return ResponseEntity.status(HttpStatus.CREATED).build();
	    	}else {
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    	}
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updateCruise(@PathVariable String id, @ModelAttribute CruiseDto cruise) throws IOException {
	        try {
	        	boolean success = cruiseService.updateCruise(id, cruise);
		        if (success) {
		            return ResponseEntity.status(HttpStatus.OK).build();
		        } else {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		        }
	        }catch(Exception e) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteCruise(@PathVariable String id) {
	        cruiseService.deleteCruise(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
