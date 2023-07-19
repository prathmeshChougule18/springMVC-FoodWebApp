package com.jsp.controller;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dao.CustomerDao;
import com.jsp.dto.Customer;
import com.jsp.dto.FoodItems;
import com.jsp.dto.FoodOrder;
import com.jsp.dto.Staff;
import com.jsp.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	FoodItems foodItems;
	
	@RequestMapping("saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute Customer customer,HttpServletRequest req) {
		HttpSession session=req.getSession();
		
		Staff staff=(Staff) session.getAttribute("Staff");
		
		FoodOrder foodOrder=new FoodOrder();
		foodOrder.setStaff(staff);
		foodOrder.setCustomer(customer);
		
		foodOrder.setWorkerName(customer.getCustomer());
		foodOrder.setCustomerName(customer.getCustomer());
		
		LocalDate date=LocalDate.now();
		LocalTime time=LocalTime.now();
		
		foodOrder.setOrderDate(date);
		foodOrder.setTime(Time.valueOf(time));
		
		ArrayList<FoodItems> items=(ArrayList<FoodItems>) session.getAttribute("allitems");
		
		foodOrder.setFoodItems(items);
		foodOrder.setNumberOfItems(items.size());
		
		session.setAttribute("myorder",foodOrder);
		session.setAttribute("customer",customer);
		
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("ordermodel", foodOrder);
		modelAndView.setViewName("orderdetails.jsp");
		return modelAndView;
	}
	
}
