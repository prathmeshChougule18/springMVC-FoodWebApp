package com.jsp.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.dto.Customer;
import com.jsp.dto.FoodItems;
import com.jsp.dto.FoodOrder;
import com.jsp.dto.Staff;

@Repository
public class FoodOrderDao {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveOrder(FoodOrder order) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(order);
		entityTransaction.commit();

		return order.getCustomerName() + "'s order is saved";
	}
	
	public void saveItem(FoodItems item) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(item);
		entityTransaction.commit();
	}

	public FoodOrder getOrder(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		return entityManager.find(FoodOrder.class, id);
	}
	
	public ArrayList<FoodOrder> getAllOrders() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Query q = entityManager.createQuery("select o from FoodOrder o");

		return (ArrayList<FoodOrder>) q.getResultList();
	}
	
	public static void main(String[] args) {
		
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.getCustomer(1);

		StaffDao staffDao = new StaffDao();
		Staff staff = staffDao.getStaff(1);

		FoodOrder order = new FoodOrder();
		order.setCustomer(customer);
		order.setStaff(staff);;
		order.setWorkerName(staff.getName());

		FoodItems foodItems = new FoodItems();
		foodItems.setItemName("Pizza");

		FoodItems foodItems2 = new FoodItems();
		foodItems2.setItemName("Burger");

		ArrayList<FoodItems> all = new ArrayList<FoodItems>();
		all.add(foodItems);
		all.add(foodItems2);

		order.setFoodItems(all);

		FoodOrderDao orderDao = new FoodOrderDao();
		orderDao.saveOrder(order);
		

	}

}
