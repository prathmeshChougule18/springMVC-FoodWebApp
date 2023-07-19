package com.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.Staff;

@Component
public class StaffDao {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	public String saveStaff(Staff staff) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(staff);
		entityTransaction.commit();

		return staff.getUsername() + " is saved";
	}

	public Staff getStaff(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		return entityManager.find(Staff.class, id);
	}
	
public Staff staffLogin(String username,String password) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
		Query query=entityManager.createQuery("select s from Staff s where username=?1 and password=?2");
		query.setParameter(1,username);
		query.setParameter(2,password);
		
		try {
			Object obj=query.getSingleResult();
			return (Staff) obj;
		}catch(NoResultException e) {
			
			return null;
		}
		
	}
}
