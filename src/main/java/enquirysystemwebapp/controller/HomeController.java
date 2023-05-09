package enquirysystemwebapp.controller;


import java.time.LocalDate;
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

import com.mysql.cj.Session;

import enquirysystemwebapp.dao.AdminDAO;
import enquirysystemwebapp.dao.BatchDAO;
import enquirysystemwebapp.dao.CourseDAO;
import enquirysystemwebapp.dao.EnquiryDAO;
import enquirysystemwebapp.dao.GalleryDAO;
import enquirysystemwebapp.dao_impl.AdminDAOImpl;
import enquirysystemwebapp.dao_impl.EnquiryDAOImpl;
import enquirysystemwebapp.helper.Message;
import enquirysystemwebapp.models.Admin;
import enquirysystemwebapp.models.Album;
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
	
	@Autowired
	GalleryDAO galleryDao;
	
	@GetMapping("/")
	public ModelAndView startPage() {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("title","Homepage - Enquiry System");
		model.addObject("coursesType","Offered Courses");
		model.addObject("month",LocalDate.now().getMonth());
		model.addObject("year",LocalDate.now().getYear());
		model.addObject("courses",courseDao.getCurrentMonthCourses());
		
		return model;
	}
	
	@GetMapping("/aboutus")
	public String getAboutUs(
			HttpServletRequest request
			) {
		return "aboutus";
	}
	
	@GetMapping("/getPastCourses")
	public ModelAndView listAllPastCourses(
			HttpSession session
			) {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("title","Past Courses");
		model.addObject("coursesType","Past Courses");
		
		List<Course> courses=courseDao.getPastCourses();
		if(courses==null) {
			session.setAttribute("message", new Message("No courses found", "secondary"));
		}
		else {
			model.addObject("courses",courses);
		}
		return model;
	}
	
	@GetMapping("/getUpcomingCourses")
	public ModelAndView listAllUpcomingCourses(
			HttpSession session
			)  {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("title","Upcoming Courses");
		model.addObject("coursesType","Upcoming Courses");
		List<Course> courses=courseDao.getUpcomingCourses();
		if(courses==null) {
			session.setAttribute("message", new Message("No courses found", "secondary"));
		}
		else {
			model.addObject("courses",courses);
		}
		return model;
	}
	
	@GetMapping("/getAllCourses")
	public ModelAndView listAllCourses(
			HttpSession session
			)  {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("title","All Courses");
		model.addObject("coursesType","All Courses");
		List<Course> courses=courseDao.getAllCourses();
		if(courses==null) {
			session.setAttribute("message", new Message("No courses found", "secondary"));
		}
		else {
			model.addObject("courses",courses);
		}
		return model;
	}
	
	@GetMapping("/contactus")
	public static ModelAndView beforeContactUsForm()
	{
		Query query=new Query();
		return new ModelAndView("contactus","myquery",query);
	}
	
	@PostMapping("/contactus")
	public void afterContactUsForm(
			@ModelAttribute("myquery") Query query,
			HttpSession session
			) {
		System.out.println(query.toString());
		
		Message message;
		if(queryDao.addQuery(query)) {
			message=new Message("Query Registered! We will contact you shortly.", "success");
		}
		else {
			message=new Message("Something went wrong, Try later!", "danger");
		}
		session.setAttribute("message", message);
	}
	
	@GetMapping("/adminlogin")
	public ModelAndView beforeAdminLogin(
			HttpSession session
			)
	{

		session.removeAttribute("admin");
		ModelAndView model=new ModelAndView();
		model.setViewName("adminlogin");
 		Admin admin=new Admin();
		model.addObject("admin",admin);
		return model;
	}
	
	@PostMapping("/adminlogin")
	public String afterAdminLogin(
			@ModelAttribute("admin") Admin admin,
			HttpSession session
			) {
		System.out.println(admin);
		AdminDAO dao=new AdminDAOImpl(admin);
		
		//If login is successful
		if(dao.login()) {
			session.setAttribute("admin", true);
			return "redirect:/admindashboard";
		}
		else {
			session.setAttribute("message", new Message("Invalid credential", "danger"));
			return "redirect:/adminlogin";
		}
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
//	
//	@GetMapping("/viewCourses")
//	public ModelAndView viewAllCourses() {
//		return new ModelAndView("admindashboard","courses",courseDao.getAllCourses());
//	}
//	
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
	
	@GetMapping("/viewGallery")
	public ModelAndView viewGallery(
			HttpSession session
			) {
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("gallery");
		List<Album> albums=galleryDao.getAllValidAlbums();
		if(albums!=null) {
			modelAndView.addObject("albums",albums);
		}
		else {
			Message message=new Message("No albums found! Add some albums.", "warning");
			session.setAttribute("message", message);
		}
		return modelAndView;
	}
	
	@GetMapping("/viewAlbumImages/{albumId}")
	public ModelAndView viewAndManageAlbumImages(
			@PathVariable("albumId") int albumId,
			HttpSession session
			) {
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("album");
		modelAndView.addObject("album",galleryDao.getAlbumById(albumId));
		
		List<String> images=galleryDao.getImagesByAlbum(albumId);
		if(images==null) {
			Message message;
			message=new Message("No images found for this album! Ask admin to add some images.", "warning");
			session.setAttribute("message", message);
		}
		else {
			modelAndView.addObject("images",images);
		}
		return modelAndView;
		
	}
	
	
}
