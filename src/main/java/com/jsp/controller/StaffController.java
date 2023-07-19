package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dao.AdminDao;
import com.jsp.dao.StaffDao;
import com.jsp.dto.Admin;
import com.jsp.dto.Staff;
import com.jsp.service.AdminService;
import com.jsp.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	StaffService staffService;
	
	@Autowired
	StaffDao staffDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
   AdminService adminService;
	
	@RequestMapping("createSatff")
	public ModelAndView createSatff() {
		
		ModelAndView modelAndView=new ModelAndView("createStaff.jsp");
		modelAndView.addObject("Satffmodel",new Staff());
		return modelAndView;
	}
	
	@RequestMapping("saveStaff")
	public ModelAndView saveStaff(@ModelAttribute Staff staff,HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
        int id =(int)httpSession.getAttribute("adminid");
        
        Admin admin =adminDao.getAdmin(id);
        
        staff.setAdmin(admin);
        
        String message=staffDao.saveStaff(staff);
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("result", message);
        modelAndView.setViewName("adminmessage.jsp");
        return modelAndView;
		
	
	}
	
	@RequestMapping("stafflogin")
	public void workerLogin(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		HttpSession session=req.getSession();
		
		Staff staff = staffDao.staffLogin(username, password);
		
		PrintWriter out=resp.getWriter();
		
		resp.setContentType("text/html");
		if(staff!=null) {
			session.setAttribute("staff",staff.getId());
			RequestDispatcher dispatcher=req.getRequestDispatcher("staffhome.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			out.println("<center><h1>INVALID CREDENTIALS</h1></center>");
			RequestDispatcher dispatcher=req.getRequestDispatcher("staffLogin.jsp");
			dispatcher.include(req, resp);
		}
			
	
	}	
}
