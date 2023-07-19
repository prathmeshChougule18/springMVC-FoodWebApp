package com.jsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.AdminDao;
import com.jsp.dto.Admin;
@Component
public class AdminService {
	@Autowired
	AdminDao adminDao;
	public String saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
}
	public List<Admin> readAllAdmin(){
		return adminDao.readAllAdmin();
	}
	public boolean deleteAdmin(int id) {
		return adminDao.deleteAdmin(id);
	}
	public void updateAdminById(Admin admin,int id) {
		adminDao.updateAdminById(admin, id);
	}
}
	

