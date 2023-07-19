package com.jsp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.FoodOrderDao;
import com.jsp.dto.FoodItems;
import com.jsp.dto.FoodOrder;

@Component
public class FoodOrderService {
	@Autowired
	FoodOrderDao foodOrderDao;
	
	public String saveOrder(FoodOrder order) {
		return foodOrderDao.saveOrder(order);
	}
	public void saveItem(FoodItems item) {
		foodOrderDao.saveItem(item);
	}
	public FoodOrder getOrder(int id) {
		return foodOrderDao.getOrder(id);
	}
	public ArrayList<FoodOrder> getAllOrders() {
		return foodOrderDao.getAllOrders();
	}
	
}
