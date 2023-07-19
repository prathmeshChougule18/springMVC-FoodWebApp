package com.jsp.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jsp.dto.Product;

@Repository
@Component
public class ProductDao {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveProduct(Product product) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();

		return product.getName() + " is saved";
	}
	
	public Product getProduct(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		return entityManager.find(Product.class, id);
	}
	
	public ArrayList<Product> getAllProduct(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		Query query = entityManager.createQuery("select p from Product p");
		
		return (ArrayList<Product>) query.getResultList();
	}
	
	public String deleteProduct(int id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		 Product product = getProduct(id);
		 entityTransaction.begin();
		 entityManager.remove(entityManager.find(Product.class,id));
		 entityTransaction.commit();
		 
		 return product.getName()+" is deleted";
		 
	}
	
	public String updateProduct(Product  product,int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		Product product2 = entityManager.find(Product.class, product.getId());
		if(product2 != null) {
			entityTransaction.begin();
			if(product.getName() !=null) {
				product2.setName(product.getName());
			}
			if(product.getDescription()!=null) {
				product2.setDescription(product.getDescription());
			}
			if(product.getImg()!=null) {
				product2.setImg(product.getImg());
			}
			
			entityManager.merge(product2);
			entityTransaction.commit();
		}
		return product.getName()+" is Updated";
 	}
	
}
