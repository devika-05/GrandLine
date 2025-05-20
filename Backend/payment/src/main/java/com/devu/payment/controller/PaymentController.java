package com.devu.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devu.payment.entity.Transaction;
import com.devu.payment.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	 @GetMapping("/{amount}")
	    public ResponseEntity<?> initiatePayment(@PathVariable(name="amount") Double amount) {
	        Transaction transaction = paymentService.createTransaction(amount);
	        // Assuming transaction contains payment URL or relevant details
	        return ResponseEntity.ok().body(transaction);
	    }
}
