package enquirysystemwebapp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import enquirysystemwebapp.dao.AdminDAO;
import enquirysystemwebapp.dao.BatchDAO;
import enquirysystemwebapp.dao.CourseDAO;
import enquirysystemwebapp.dao.EnquiryDAO;
import enquirysystemwebapp.dao_impl.AdminDAOImpl;
import enquirysystemwebapp.dao_impl.EnquiryDAOImpl;
import enquirysystemwebapp.helper.Message;
import enquirysystemwebapp.models.Admin;
import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.Query;
import enquirysystemwebapp.models.Batch;


@Controller
public class HomeController {
	@Autowired
	CourseDAO courseDao;
	
	@Autowired
	BatchDAO batchDao;
	
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
	public String getAboutUs(
			HttpServletRequest request
			) {
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
		ModelAndView model=new ModelAndView();
		model.setViewName("adminlogin");
 		Admin admin=new Admin();
		model.addObject("admin",admin);
		return model;
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
	public ModelAndView manageCourses(
			HttpSession session
			) {
		//clean up
		session.removeAttribute("enquiryId");
		//action
		ModelAndView model=new ModelAndView();
		model.setViewName("admindashboard");
		model.addObject("title", "Admin Panel");
		return model;
	}
	
	@GetMapping("/viewCourse/{courseId}")
	public ModelAndView viewCourse(
			@PathVariable("courseId") int courseId,
			HttpSession session
			) {
		ModelAndView modelAndView=new ModelAndView();
		
		Course course=courseDao.getCourseById(courseId);
		List<Batch> batches=batchDao.getBatchesByCourseId(courseId);
		
		modelAndView.addObject("course",course);
		modelAndView.addObject("batches",batches);
		modelAndView.addObject("countOfBatches",batches.size());
		modelAndView.addObject("noOfDays",courseDao.getNumberOfDaysOfCourseByCourseId(courseId));
		
		List<String> ytVideos=courseDao.getYtLinksForCreativeCornerByCourseId(courseId);
		if(ytVideos==null) {
			session.setAttribute("message", new Message("No videos found! Ask admin to add more videos.","warning"));
		}
		modelAndView.addObject("links",ytVideos);
		modelAndView.setViewName("schedule");
		return modelAndView;
		
	}
	
	
}
