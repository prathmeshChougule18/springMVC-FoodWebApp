package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dto.Admin;


@Component
public class AdminDao {
@Autowired
EntityManagerFactory entityManagerFactory;


public String saveAdmin(Admin admin) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	entityTransaction.begin();
	entityManager.persist(admin);
	entityTransaction.commit();
	return admin.getUsername()+" Is Saved";
	
}
public List<Admin> readAllAdmin(){
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	String sql ="Select a From Admin a";
	Query query = entityManager.createQuery(sql);
	List<Admin> admins = query.getResultList();
	return admins;
}

  public boolean deleteAdmin(int id) {
	  EntityManager entityManager = entityManagerFactory.createEntityManager();
	  EntityTransaction entityTransaction = entityManager.getTransaction();
	  
		entityTransaction.begin();
		entityManager.persist(entityManager.find(Admin.class,id));
		entityTransaction.commit();
		return true;
  }
   
  public void updateAdminById(Admin admin,int id) {
	  EntityManager entityManager = entityManagerFactory.createEntityManager();
	  EntityTransaction entityTransaction = entityManager.getTransaction();
	 
	  Admin admin2 = entityManager.find(Admin.class, admin.getId());
	  if(admin2 !=null) {
		  entityTransaction.begin();
		  if(admin.getName() != null) {
			  admin2.setName(admin.getName());
		  }
		  if(admin.getUsername() != null) {
			  admin2.setUsername(admin.getUsername());
		  }
		  if(admin.getPassword() != null) {
			  admin2.setPassword(admin.getPassword());
		  }
		  entityManager.merge(admin2);
		  entityTransaction.commit();
	  }
	  
  }
  
  public Admin getAdmin(int id) {
	  EntityManager entityManager = entityManagerFactory.createEntityManager();
	  EntityTransaction entityTransaction = entityManager.getTransaction();
	 
	  return entityManager.find(Admin.class, id);
  }
  
  public Admin findUsernamePassword(String username, String password) {
	  EntityManager entityManager = entityManagerFactory.createEntityManager();
	  EntityTransaction entityTransaction = entityManager.getTransaction();
	 
	  Query query = entityManager.createQuery("select o from Admin  o where username=?1 and password=?2");
	  query.setParameter(1, username);
	  query.setParameter(2, password);
	  
	  try {
		  return (Admin) query.getSingleResult();
	  }catch (NoResultException e) {
		  return null;
	}
  }
  
}
