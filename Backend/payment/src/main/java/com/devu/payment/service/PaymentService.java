package com.devu.payment.service;

import com.devu.payment.entity.Transaction;

public interface PaymentService {


	public Transaction createTransaction(double amount);
}