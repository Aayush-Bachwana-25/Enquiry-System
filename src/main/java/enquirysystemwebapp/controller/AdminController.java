package enquirysystemwebapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import enquirysystemwebapp.dao.BatchDAO;
import enquirysystemwebapp.dao.CourseDAO;
import enquirysystemwebapp.dao.EnquiryDAO;
import enquirysystemwebapp.dao_impl.CourseDAOImpl;
import enquirysystemwebapp.helper.Message;
import enquirysystemwebapp.models.Batch;
import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.Feedback;
import enquirysystemwebapp.models.Query;

@Controller
public class AdminController {
	@Autowired
	CourseDAO courseDao;
	@Autowired
	BatchDAO batchDao;
	
	@Autowired
	EnquiryDAO enquiryDao;
	
	@GetMapping("/addcourse")
	public ModelAndView addNewCourse(){
		Course course=new Course();
		return new ModelAndView("addcourse","nc",course);
	}
	
//	@PostMapping("/addcourse")
//	public ModelAndView postaddNewCourse(@ModelAttribute("nc") Course course,HttpSession session){
//		
//		ModelAndView model=new ModelAndView();
//		
//		String msg;
//		
//		if(courseDao.addCourse(course,session)) {
//			msg="Upload Successful!";
//			model.setViewName("addBatch");
//			model.addObject("ns", new Batch());
//			model.addObject("filename",course.getImageData().getOriginalFilename());
//			session.setAttribute("message", new Message("Course added successfully!", "success"));
//		}
//		else {
//			model.setViewName("admindashboard");
//			msg="Upload error";
//			session.setAttribute("message", new Message("Something went wrong,Try again!", "danger"));
//		}
//		model.addObject("msg", msg);
//		return model;
//	}
	
	@PostMapping("/addcourse")
	public void postaddNewCourse(@ModelAttribute("nc") Course course,HttpSession session,HttpServletRequest request){	
		Message message;
		if(courseDao.addCourse(course,session)) {
			message=new Message("Course added successfully!", "success");
		}
		else {
			message=new Message("Something went wrong,Try again!", "danger");
		}
		request.setAttribute("message",message);
	}
	
	@GetMapping("/managecourses")
	public ModelAndView manageCourses() {
		ModelAndView model=new ModelAndView();
		model.setViewName("manageCourses");
		model.addObject("title", "Manage Courses");
		model.addObject("courses",courseDao.getAllCourses());
		return model;
	}
	
	@GetMapping("/delete/{courseId}")
	public RedirectView deleteCourse(@PathVariable("courseId") int courseId,HttpServletRequest request) {
		courseDao.deleteCourse(courseId);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/managecourses");
		return redirectView;
	}
	
	@GetMapping("/editCourse/{courseId}")
	public ModelAndView editCourse(@PathVariable("courseId") int courseId) {
		ModelAndView model=new ModelAndView();
		model.setViewName("modifyCourse");
		model.addObject("title", "Edit Course");
		model.addObject("course",courseDao.getCourseById(courseId));
		return model;	
	}
	
	@PostMapping("/editCourse/{courseId}")
	public RedirectView editCourse(@ModelAttribute Course course,HttpSession session,HttpServletRequest request) {
		courseDao.updateCourse(course, session);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/managecourses");
		return redirectView;
	}
	
	@GetMapping("/addBatch/{courseId}/{courseName}")
	public ModelAndView addBatchForCourse(@PathVariable("courseId") int courseId,@PathVariable("courseName") String courseName,HttpServletRequest request) {
		Batch batch=new Batch(courseId);
		ModelAndView model=new ModelAndView();
		model.setViewName("/addBatch");
		model.addObject("ns",batch);
		request.setAttribute("courseName",courseName);
		return model;
	}
	
	@PostMapping("/addBatch/{courseId}/{courseName}")
    public RedirectView addBatchForCourse(@ModelAttribute("ns") Batch batch,
    		@PathVariable("courseId") int courseId,
    		@PathVariable("courseName") String courseName,
    		HttpSession session,
    		HttpServletRequest request) {
		
		Message message;
		System.out.println(batch);
    	batch.setCourseId(courseId);
    	if(batchDao.addBatch(batch)) {
    		message=new Message("Batch added successfully!", "success");
    	}
    	else {
    		message=new Message("Something went wrong,Try again!", "danger");
    	}
    	session.setAttribute("message",message);
    	System.out.println(message);
    	
    	RedirectView redirectView=new RedirectView();
//    	redirectView.setUrl(request.getContextPath()+"/addBatch/"+courseId+"/"+courseName);
    	redirectView.setUrl(request.getContextPath()+"/managebatches");
		return redirectView;
		
    	
    }
	
	@GetMapping("/managebatches")
	public ModelAndView showCoursesForManagingBatches() {
		ModelAndView model=new ModelAndView();
		model.setViewName("manageBatches");
		
		List<Course> courses= courseDao.getAllCourses();
		model.addObject("courses",courses);
		return model;
	}
	
	@GetMapping("/manageBatchByCourse/{courseId}/{courseName}")
	public ModelAndView manageBatchByCourse(@PathVariable("courseId") int courseId,
			@PathVariable("courseName") String courseName,
			HttpServletRequest request) {
		List<Batch> batches=batchDao.getBatchesByCourseId(courseId);
		ModelAndView model=new ModelAndView();
		model.setViewName("manageBatchesForCourse");
		model.addObject("batches",batches);
		model.addObject("title","Batches: "+courseName);
		
		//setting course details
		request.setAttribute("courseName", courseName);
		
		return model;
	}
	
	@GetMapping("/manageBatchByCourse/{courseId}/deleteBatch/{id}")
	public RedirectView deleteBatchForCourse(@PathVariable("id") int batchId,
			@PathVariable("courseId") int courseId,
			HttpServletRequest request,
			HttpSession session) {
		Message message;
		if(batchDao.deleteBatch(batchId)) {
			System.out.println("Batch deleted");
			message=new Message("Batch deleted successfully!", "success");
		}
		else {
			message=new Message("Something went wrong,Try again!", "danger");
		}
		session.setAttribute("message",message);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/managebatches");
		return redirectView;
	}
	
	@GetMapping("/viewqueries")
	public ModelAndView viewAndManageQueries() {
		ModelAndView model=new ModelAndView();
		model.setViewName("viewQueries");
		
		List<Query> enquiries=enquiryDao.getAllQueriesForToday();
		model.addObject("enquiries",enquiries);
		model.addObject("title","Today's Queries");
		return model;
	}
	
	@GetMapping("/followUpUser/{id}")
	public ModelAndView viewUserProfile(@PathVariable("id") int enquiryId) {
		ModelAndView model=new ModelAndView();
		model.setViewName("followUser");
		
		List<Feedback> feedbackHistory=enquiryDao.getFeedbackHistoryForQuery(enquiryId);
		Query enquiry=enquiryDao.getQueryById(enquiryId);
		
		model.addObject("enquiry",enquiry);
		model.addObject("feedbackHistory",feedbackHistory);
		model.addObject("title","Follow-up");
		return model;
	}
	
	@PostMapping("/followUpUser/saveFollowUp")
	public RedirectView saveFollowUp(@RequestParam("followDate") String updatedFollowDate,
			@RequestParam("feedback") String feedback,
			HttpServletRequest request,
			HttpSession session) {
		
		
		Message message;
		
		int enquiryId=(Integer)session.getAttribute("enquiryId");
		
		
		//********************************
		//Check details
		System.out.println(updatedFollowDate);
		System.out.println(feedback);
		System.out.println(enquiryId);
		//******************************
				
		
		boolean action1=enquiryDao.updateFollowDate(enquiryId, updatedFollowDate);
		boolean action2=enquiryDao.addFeedbackForQuery(new Feedback(enquiryId,feedback));
		
		session.removeAttribute("enquiryId");
		
		
		if(action1==true && action2==true) {
			message=new Message("Update successful!", "success");
		}
		else {
			message=new Message("Something went wrong!", "danger");
		}
		
		session.setAttribute("message", message);
		
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/followUpUser/"+enquiryId);
		session.removeAttribute("enquiryId");
		return redirectView;
	}
	
	
}
