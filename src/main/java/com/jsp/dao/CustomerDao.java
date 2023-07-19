package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.dto.Customer;

@Repository
public class CustomerDao {
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	 public String saveCustomer(Customer customer) {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
		   EntityTransaction entityTransaction = entityManager.getTransaction();
			
			entityTransaction.begin();
			entityManager.persist(customer);
			entityTransaction.commit();
			
			return customer.getCustomer()+" is saved";
		}

		
		public Customer getCustomer(int id) {
			EntityManager entityManager= entityManagerFactory.createEntityManager();
			EntityTransaction entityTransaction=entityManager.getTransaction();
			return entityManager.find(Customer.class,id);
		}
		public static void main(String[] args) {
			
			Customer c=new Customer();
			c.setCustomer("ravi");
			c.setMobileNo(989383);
			c.setCity("Rajajinagar");
			
			CustomerDao dao=new CustomerDao();
			dao.saveCustomer(c);
		}
}
