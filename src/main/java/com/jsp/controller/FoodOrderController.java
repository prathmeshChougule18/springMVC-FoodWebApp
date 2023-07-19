package com.jsp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dao.CustomerDao;
import com.jsp.dao.FoodOrderDao;
import com.jsp.dao.ProductDao;
import com.jsp.dto.Customer;
import com.jsp.dto.FoodOrder;
import com.jsp.dto.Product;

@Controller
public class FoodOrderController {
 @Autowired
 ProductDao productDao;
 @Autowired
 CustomerDao customerDao;
 @Autowired
 FoodOrderDao foodOrderDao;
  
  @RequestMapping("createOrder")
	public ModelAndView createOrder() {
		
		ArrayList<Product> products=productDao.getAllProduct();
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("allproducts",products);
		modelAndView.setViewName("displayfooditems.jsp");
		return modelAndView;
	}
  @RequestMapping("confirm")
	public ModelAndView confirmOrder(HttpServletRequest req) {
		
		HttpSession session=req.getSession();
		
		Customer customer=(Customer) session.getAttribute("customer");
		customerDao.saveCustomer(customer);
		
		FoodOrder foodOrder=(FoodOrder) session.getAttribute("myorder");
		
		foodOrderDao.saveOrder(foodOrder);
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("ordermodel",foodOrder);
		modelAndView.setViewName("final.jsp");
		return modelAndView;
	}
	
	@RequestMapping("refresh")
	public ModelAndView refresh(HttpServletRequest req) {
		
		HttpSession session=req.getSession();
		
		session.removeAttribute("customer");
		session.removeAttribute("myorder");
		session.removeAttribute("allitems");
		
		ModelAndView mv=new ModelAndView();
		
		mv.setViewName("createOrder");
		return mv;
	}
}
