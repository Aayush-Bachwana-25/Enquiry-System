package enquirysystemwebapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import enquirysystemwebapp.dao.AdminDAO;
import enquirysystemwebapp.dao.BatchDAO;
import enquirysystemwebapp.dao.CourseDAO;
import enquirysystemwebapp.dao.EnquiryDAO;
import enquirysystemwebapp.models.Batch;

@Controller
public class AppController {
	@Autowired
	AdminDAO adminDao;
	
	@Autowired
	BatchDAO batchDao;
	
	@Autowired
	CourseDAO courseDao;
	
	@Autowired
	EnquiryDAO queryDao;
	
	
	
	@GetMapping("/viewCourses")
	public ModelAndView viewAllCourses() {
		return new ModelAndView("admindashboard","courses",courseDao.getAllCourses());
	}
}
