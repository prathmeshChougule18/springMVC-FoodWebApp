package com.jsp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.ProductDao;
import com.jsp.dto.Product;

@Component
public class ProductService {
  @Autowired
  ProductDao productDao;
  
  public String saveProduct(Product product) {
	  return productDao.saveProduct(product);
  }
  
  public Product getProduct(int id) {
	  return productDao.getProduct(id);
  }
  
  public ArrayList<Product> getAllProduct(){
	  return productDao.getAllProduct();
  }
  public String deleteProduct(int id) {
		return productDao.deleteProduct(id);
  }
  
  public String updateProduct(Product  product,int id) {
	  return productDao.updateProduct(product, id);
  }
  
}
