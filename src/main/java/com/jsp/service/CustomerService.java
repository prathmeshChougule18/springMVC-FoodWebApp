package com.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.CustomerDao;
import com.jsp.dto.Customer;

@Component
public class CustomerService {
   @Autowired
	CustomerDao customerDao;
   public String saveCustomer(Customer customer) {
	   return customerDao.saveCustomer(customer);
   }
   
   public Customer getCustomer(int id) {
	   return customerDao.getCustomer(id);
   }
}
