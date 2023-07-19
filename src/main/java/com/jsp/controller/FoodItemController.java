package com.jsp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dao.ProductDao;
import com.jsp.dto.Customer;
import com.jsp.dto.FoodItems;
import com.jsp.dto.Product;
import com.jsp.service.BillService;

@Controller
public class FoodItemController {
 
	@Autowired
	ProductDao productDao;
	
	@Autowired
	BillService billService;
	
	@RequestMapping("addItem")
	public ModelAndView addItem(@RequestParam int id) {
		
	   Product product=productDao.getProduct(id);		
		FoodItems item=new FoodItems();
		
		item.setItemName(product.getName());
		item.setPrice(product.getCost());
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("item",item);
		mv.setViewName("quantity.jsp");
		return mv;
	}
	
	@RequestMapping("toOrder")
	public ModelAndView toOrder(@ModelAttribute FoodItems item,HttpServletRequest req) {
		
		HttpSession session=req.getSession();
		
		Object obj=session.getAttribute("allitems");
		
		double totalCost=billService.calculate(item.getQuantity(),item.getPrice());
		
		item.setTotalCost(totalCost);
		
		if(obj==null) {
			
			ArrayList<FoodItems> items=new ArrayList<FoodItems>();
			items.add(item);
			session.setAttribute("allitems", items);
		}else {
			
			ArrayList<FoodItems> items=(ArrayList<FoodItems>) session.getAttribute("allitems");
			items.add(item);
			session.setAttribute("allitems", items);
		}
		System.out.println(obj);
		
		ArrayList<Product> products=productDao.getAllProduct();
		ModelAndView modelAndView=new ModelAndView();
	    modelAndView.setViewName("createOrder");
		return modelAndView;
	}
	
	@RequestMapping("cart")
	public ModelAndView cart(HttpServletRequest req) {
		HttpSession session=req.getSession();
		ArrayList<FoodItems> items=(ArrayList<FoodItems>) session.getAttribute("allitems");
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("myitems",items);
		modelAndView.setViewName("cart.jsp");
		return modelAndView;
	}
	
	@RequestMapping("deleteItem")
	public ModelAndView deleteItem(@RequestParam int value,HttpServletRequest req) {
		
		HttpSession session=req.getSession();
		
		ArrayList<FoodItems> items=(ArrayList<FoodItems>) session.getAttribute("allitems");
		items.remove(value);
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("myitems",items);
		modelAndView.setViewName("cart.jsp");
		return modelAndView;
	}
	
	
	@RequestMapping("next")
	public ModelAndView next() {
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("customermodel",new Customer());
		modelAndView.setViewName("customer.jsp");
		return modelAndView;
	}
	
}
