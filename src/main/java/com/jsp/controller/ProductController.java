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

import com.jsp.dao.AdminDao;
import com.jsp.dto.Admin;
import com.jsp.dto.Product;
import com.jsp.service.ProductService;

@Controller
public class ProductController {

	 @Autowired
	 ProductService productService;	
	
	@Autowired
	AdminDao adminDao;

 

 
 @RequestMapping("/createProduct")
  public ModelAndView createPorduct() {
	 ModelAndView modelAndView = new ModelAndView("createProduct.jsp");
	 modelAndView.addObject("productmodel",new Product());
	 return modelAndView;
 
 }
 @RequestMapping("/saveProduct")
 public ModelAndView saveProduct(@ModelAttribute Product product,HttpServletRequest req) {
	 HttpSession session=req.getSession();
	 int id =(int) session.getAttribute("adminid");
			 
	 Admin admin=adminDao.getAdmin(id);
	product.setAdmin(admin);
	 
	 String message=productService.saveProduct(product);
	 ModelAndView modelAndView = new ModelAndView();
	 modelAndView.addObject("result", message);
	 modelAndView.setViewName("adminmessage.jsp");
	 return modelAndView;
 }

  @RequestMapping("displayproduct")
  public ModelAndView allProduct() {
	  
	  ArrayList<Product>products=productService.getAllProduct();
	  
	  ModelAndView modelAndView = new ModelAndView();
	  modelAndView.addObject("allproducts",products );
	  modelAndView.setViewName("displayProduct.jsp");
	  return modelAndView;
	  
  }

 @RequestMapping("deleteProduct")
 public ModelAndView delteProduct(@RequestParam(name = "id")int id) {
	 
	 String message =productService.deleteProduct(id);
	 ModelAndView modelAndView = new ModelAndView(); 
	 modelAndView.addObject("result", message);
	 modelAndView.setViewName("adminmessage.jsp");
	 return modelAndView;
	  
 }
 
 @RequestMapping("updateProduct")
 public ModelAndView updateProducts(@RequestParam(name = "id")int id) {
	 
	 
	 ModelAndView modelAndView = new ModelAndView("productUpdated.jsp");
	 modelAndView.addObject("result", new Product());
	 return modelAndView;
 }
  
 @RequestMapping("productupdated")
 public ModelAndView updateProductFinally(@ModelAttribute Product product) {
	 productService.updateProduct(product, product.getId());
	 ModelAndView modelAndView = new ModelAndView("adminmessage.jsp");
	 modelAndView.addObject("updatedoneproduct", "The admin with following" + "id" + product.getId() + "updated");
	 return modelAndView;
		
 }
}
