package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dao.AdminDao;
import com.jsp.dto.Admin;
import com.jsp.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminDao adminDao;
	
   
    
	@RequestMapping("/adminload")
	public ModelAndView lodeAdmin() {
		ModelAndView modelAndView = new ModelAndView("createAdmin.jsp");
		modelAndView.addObject("admin", new Admin());
		return modelAndView;
	}

	@RequestMapping("/adminsave")
	public ModelAndView saveAdmin(@ModelAttribute Admin admin) {
		adminService.saveAdmin(admin);
		ModelAndView modelAndView = new ModelAndView("home.jsp");
		modelAndView.addObject("save", admin.getName() + "saved successfull");
		return modelAndView;
	}

	@RequestMapping("/viewAllAdmin")
	public ModelAndView viewAll() {
		List<Admin> admins = adminService.readAllAdmin();
		ModelAndView modelAndView = new ModelAndView("viewAdmin.jsp");
		modelAndView.addObject("Adminview", admins);
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView deleteById(@RequestParam(name = "id")int id) {
		ModelAndView modelAndView = new ModelAndView("deleted.jsp");
        adminService.deleteAdmin(id);
        modelAndView.addObject("deleted", " admin with "+ id + "deleted");
        return modelAndView;
	}
	
	@RequestMapping("/update")
	public ModelAndView updateAdminById(@RequestParam(name = "id") int id) {
		ModelAndView modelAndView = new ModelAndView("updated.jsp");
		modelAndView.addObject("admin", new Admin());
		return modelAndView;
	}
	
	@RequestMapping("/adminupdated")
	public ModelAndView updatedAdminFinally(@ModelAttribute Admin admin) {
		adminService.updateAdminById(admin, admin.getId());
		ModelAndView modelAndView = new ModelAndView("updatedone.jsp");
		modelAndView.addObject("updatedone", "The admin with following" + "id" + admin.getId() + "updated");
		return modelAndView;
	}
	
	@RequestMapping("/adminLogin")
	public void adminLogin(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
      String username=req.getParameter("username");
      String password=req.getParameter("password");
      
      Admin admin= adminDao.findUsernamePassword(username, password);
      
      PrintWriter out=resp.getWriter();
      HttpSession session=req.getSession();
      resp.setContentType("text/html");
      if(admin != null) {
    	  session.setAttribute("adminid",admin.getId());
    	  RequestDispatcher dispatcher =req.getRequestDispatcher("adminHome.jsp");
         dispatcher.forward(req, resp);
      }else {
    	  
    	  out.println("<center><h1>INVALIDID DETAILS</h1></center>");
    	  RequestDispatcher dispatcher =req.getRequestDispatcher("adminLogin.jsp");
          dispatcher.include(req, resp);
	}
      
}
}	
