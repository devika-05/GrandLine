package com.devu.cruiseBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CruiseBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruiseBookApplication.class, args);
	}

}
