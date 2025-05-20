package com.devu.payment.service;

import com.devu.payment.entity.Transaction;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static final String KEY = "rzp_test_YiAgR8tagZNvmj";
	private static final String KEY_SECRET = "DlUilh2oMaN1DQoL9aFz5r4T";
	private static final String CURRENCY = "INR";
	
	@Override
	public Transaction createTransaction(double amount) 
	{
		//amount
		//currency
		//key          ---(From Razorpay portal)
		//secret key  ---(From Razorpay portal)
		
		
		//creating a razorpay client object
		//we have to call the create method of RazorpayClient
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount * 100));  //*100 -->Razorpay always considers smallest unit of any currency .i.e., paisa for INR
			jsonObject.put("currency", CURRENCY);			
			
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			
			Order order = razorpayClient.orders.create(jsonObject);  //.create(takes JSON object as an argument)  
			
			//System.out.println(order);
			
			return prepareTransactionDetails(order);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}		
		return null; //in case if any exception occurs
	}
	
	private Transaction prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		Transaction transactionDetails = new Transaction(orderId, currency, amount, KEY);
		return transactionDetails;
	}
}
