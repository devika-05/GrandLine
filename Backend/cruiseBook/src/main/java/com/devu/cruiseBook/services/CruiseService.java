package com.devu.cruiseBook.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devu.cruiseBook.entities.Cruise;
import com.devu.cruiseBook.entities.CruiseDto;
import com.devu.cruiseBook.repository.CruiseRepository;

@Service
public class CruiseService {
	
	 @Autowired
	    private CruiseRepository cruiseRepository;

	    public List<CruiseDto> getAllCruises() {
	        return cruiseRepository.findAll().stream().map(Cruise::getCruiseDto).collect(Collectors.toList());
	    }

	    public CruiseDto getCruiseById(String id) {
	    	Optional<Cruise>optionalCruise=cruiseRepository.findById(id);
	        return optionalCruise.map(Cruise::getCruiseDto).orElse(null);
	    }

//	    public Cruise addCruise(Cruise cruise, MultipartFile image) throws IOException {
//	    	if (cruise == null) {
//	            throw new IllegalArgumentException("Cruise data is null");
//	        }
//	        
//	        if (image == null || image.isEmpty()) {
//	            throw new IllegalArgumentException("Image file is missing");
//	        }
//
//	        cruise.setImageData(image.getBytes());
//	        return cruiseRepository.save(cruise);
//	    }
	    
	    public boolean addCruise(CruiseDto cruiseDto) throws IOException {
	    	try {
		    	Cruise cruise= new Cruise();
		    	cruise.setId(cruiseDto.getId());
		    	cruise.setCruiseId(cruiseDto.getCruiseId());
		    	cruise.setName(cruiseDto.getName());
		    	cruise.setDeparturePort(cruiseDto.getDeparturePort());
		    	cruise.setDestination(cruiseDto.getDestination());
		    	cruise.setDepartureDate(cruiseDto.getDepartureDate());
		    	cruise.setPrice(cruiseDto.getPrice());
		    	cruise.setAvailableRooms(cruiseDto.getAvailableRooms());
		    	cruise.setImage(cruiseDto.getImage().getBytes());	    
		    	cruise.setMaxCapacity(cruiseDto.getAvailableRooms());		    	
		    	cruiseRepository.save(cruise);
		    	return true;
	    	}catch (Exception e) {
				// TODO: handle exception
	    		return false;
			}
	    }

	    public boolean updateCruise(String id, CruiseDto cruiseDto) throws IOException {
	        Optional<Cruise> optionalCruise = cruiseRepository.findById(id);
	        if (optionalCruise.isPresent()) {
	        	Cruise existingCruise=optionalCruise.get();
	        	if(cruiseDto.getImage()!=null) {
	        		existingCruise.setImage(cruiseDto.getImage().getBytes());
	        	}
	        	existingCruise.setCruiseId(cruiseDto.getCruiseId());
	        	existingCruise.setDepartureDate(cruiseDto.getDepartureDate());
	        	existingCruise.setAvailableRooms(cruiseDto.getAvailableRooms());
	        	existingCruise.setDeparturePort(cruiseDto.getDeparturePort());
	        	existingCruise.setDestination(cruiseDto.getDestination());
	        	existingCruise.setName(cruiseDto.getName());
	        	existingCruise.setPrice(cruiseDto.getPrice());
	        	existingCruise.setMaxCapacity(cruiseDto.getAvailableRooms());
	            cruiseRepository.save(existingCruise);
	            return true;
	         }else {
	        	 return false;
	         }
	    }

	    public String deleteCruise(String id) {
	        cruiseRepository.deleteById(id);
	        return "Deleted";
	    }

		public Cruise getCruiseByCruiseId(String cruiseId) {
			Cruise cruise=cruiseRepository.findByCruiseId(cruiseId);
	        return cruise;
	    
		}

		

}
