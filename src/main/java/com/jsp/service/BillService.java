package com.jsp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.FoodItems;

@Component
public class BillService {
	@Autowired
	FoodItems foodItems;
	
	public double calculate(int quantity, double price) {

		return quantity * price;
	}

	public double totalBill(ArrayList<FoodItems> items) {

		double totalAmt = 0;
		for (FoodItems item : items) {

			double cost = item.getQuantity() * item.getPrice();
			totalAmt = totalAmt + cost;
		}
		return totalAmt;
	}
}
