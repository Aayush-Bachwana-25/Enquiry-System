package enquirysystemwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import enquirysystemwebapp.dao.AdminDAO;
import enquirysystemwebapp.dao.CourseDAO;
import enquirysystemwebapp.dao.EnquiryDAO;
import enquirysystemwebapp.dao_impl.AdminDAOImpl;
import enquirysystemwebapp.dao_impl.EnquiryDAOImpl;
import enquirysystemwebapp.models.Admin;
import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.Query;


@Controller
public class HomeController {
	@Autowired
	CourseDAO courseDao;
	
	@Autowired
	EnquiryDAO queryDao;
	
	@GetMapping("/")
	public ModelAndView startPage() {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("title","Homepage - Enquiry System");
		model.addObject("courses",courseDao.getAllCourses());
		return model;
	}
	
	@GetMapping("/home")
	public ModelAndView toHome() {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("title","Homepage - Enquiry System");
		model.addObject("courses",courseDao.getAllCourses());
		return model;
	}
	
	@GetMapping("/aboutus")
	public String getAboutUs() {
		return "aboutus";
	}
	
	
	@GetMapping("/contactus")
	public static ModelAndView beforeContactUsForm()
	{
		Query query=new Query();
		return new ModelAndView("contactus","myquery",query);
	}
	
	@PostMapping("/contactus")
	public void afterContactUsForm(@ModelAttribute("myquery") Query query) {
		System.out.println(query.toString());
		queryDao.addQuery(query);
	}
	
	@GetMapping("/adminlogin")
	public ModelAndView beforeAdminLogin()
	{
 		Admin admin=new Admin();
		return new ModelAndView("adminlogin","admin",admin);
	}
	
	/*
	 * @PostMapping("/adminlogin") public String
	 * afterAdminLogin(@ModelAttribute("admin") Admin admin) {
	 * System.out.println(admin); AdminDAO dao=new AdminDAOImpl(admin);
	 * 
	 * //If login is successful if(dao.login()) { return "admindashboard"; } return
	 * "failure"; }
	 */
	
	@PostMapping("/adminlogin")
	public String afterAdminLogin(@ModelAttribute("admin") Admin admin) {
		System.out.println(admin);
		AdminDAO dao=new AdminDAOImpl(admin);
		
		//If login is successful
		if(dao.login()) {
			return "admindashboard";
		}
		return "failure";
	}
	
	@GetMapping("/admindashboard")
	public ModelAndView manageCourses() {
		ModelAndView model=new ModelAndView();
		model.setViewName("admindashboard");
		model.addObject("title", "Admin Panel");
		return model;
	}
	
	
}
