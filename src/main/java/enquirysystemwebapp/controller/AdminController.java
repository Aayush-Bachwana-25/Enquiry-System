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
import enquirysystemwebapp.dao.GalleryDAO;
import enquirysystemwebapp.dao.StudentDAO;
import enquirysystemwebapp.dao_impl.CourseDAOImpl;
import enquirysystemwebapp.helper.Message;
import enquirysystemwebapp.models.Album;
import enquirysystemwebapp.models.Batch;
import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.CreativeCornerModel;
import enquirysystemwebapp.models.Feedback;
import enquirysystemwebapp.models.Query;
import enquirysystemwebapp.models.Student;
import enquirysystemwebapp.models.Transaction;

@Controller
public class AdminController {
	@Autowired
	CourseDAO courseDao;
	@Autowired
	BatchDAO batchDao;
	@Autowired
	StudentDAO studentDao;
	@Autowired
	EnquiryDAO enquiryDao;
	@Autowired
	GalleryDAO galleryDao;
	
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
	public RedirectView postaddNewCourse(@ModelAttribute("nc") Course course,
			HttpSession session,
			HttpServletRequest request){	
		
		Message message;
		int courseId=courseDao.addCourse(course,session) ;
		
		if(courseId!=-1){
			course.setCourseId(courseId);
			message=new Message("Course added successfully!", "success");
		}
		else {
			message=new Message("Something went wrong,Try again!", "danger");
		}
		session.setAttribute("message",message);
		
		RedirectView redirectView=new RedirectView();
	
		redirectView.setUrl(request.getContextPath()+"/addBatch/"+course.getCourseId()+"/"+course.getCourseName());
		return redirectView;
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
	
	@GetMapping("/manageBatchByCourse/{courseId}/")
	public ModelAndView manageBatchByCourse(@PathVariable("courseId") int courseId,
			HttpServletRequest request) {
		List<Batch> batches=batchDao.getBatchesByCourseId(courseId);
		Course course=courseDao.getCourseById(courseId);
		ModelAndView model=new ModelAndView();
		model.setViewName("manageBatchesForCourse");
		model.addObject("batches",batches);
		model.addObject("course",course);
		model.addObject("title","Batches: "+course.getCourseName());	
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
		redirectView.setUrl(request.getContextPath()+"/manageBatchByCourse/"+courseId+"/");
		return redirectView;
	}
	
	@GetMapping("/manageBatchByCourse/{courseId}/editBatch/{id}")
	public ModelAndView editBatchForCourse(@PathVariable("id") int batchId,
			@PathVariable("courseId") int courseId,
			HttpServletRequest request,
			HttpSession session) {

		ModelAndView model=new ModelAndView();
		
		Batch batch=batchDao.getBatchById(batchId);
		if(batch!=null) {
			model.setViewName("editBatch");
			model.addObject("batch",batch);		}
		else {
			model.setViewName(request.getContextPath()+"/manageBatchByCourse/"+courseId+"/");
		}
		return model;
	}
	
	@PostMapping("/manageBatchByCourse/{courseId}/editBatch/{id}")
	public RedirectView editBatchForCourse(@ModelAttribute("batch") Batch batch,
			HttpServletRequest request,
			HttpSession session) {
		Message message;
		
		RedirectView redirectView=new RedirectView();
		if(batchDao.updateBatch(batch)) {
			message=new Message("Batch updated succesfully!", "success");
		}
		else {
			message=new Message("Something went wrong!", "danger");
		}
		session.setAttribute("message", message);
		redirectView.setUrl(request.getContextPath()+"/manageBatchByCourse/"+batch.getCourseId()+"/");
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
	
	
	@GetMapping("/addStudent")
	public ModelAndView addStudent(
			HttpSession session
			) {
		ModelAndView model=new ModelAndView();
		model.setViewName("registerStudent");
		
		Student student=new Student();
		
		if(session.getAttribute("enquiryId")!=null) {
			Query query=enquiryDao.getQueryById((Integer)session.getAttribute("enquiryId"));
			student.setStudentName(query.getCandidateName());
			student.setEmail(query.getEmail());
			student.setFatherName(query.getParentName());
			student.setMobile(query.getMobile());
		}
		
		model.addObject("student",student);
		model.addObject("courses",courseDao.getAllCourses());
		model.addObject("title","Add Student");
		
		return model;
	}
	
	@PostMapping("/addStudent")
	public RedirectView addStudent(@ModelAttribute("student") Student student,
			@RequestParam("profile") MultipartFile profilePhoto,
			@RequestParam("sign") MultipartFile signature,
			@RequestParam("courseId") int courseId,
			HttpServletRequest request,
			HttpSession session) {
		
		RedirectView redirectView=new RedirectView();
		Message message;

		int studentId=studentDao.addStudent(student,profilePhoto,signature,session);
		if(studentId==-1) {
			redirectView.setUrl(request.getContextPath()+"/addStudent");
			message=new Message("Something went wrong", "danger");
			System.out.println("Registration failed!");
		}
		else{
			session.setAttribute("courseId", courseId);
			redirectView.setUrl(request.getContextPath()+"/addStudent/"+studentId);
			message=new Message("Registration successful", "success");
			System.out.println("Student registered!");
		}
		session.setAttribute("message", message);
		return redirectView;
	}
	
	@GetMapping("/addStudent/{studentId}")
	public ModelAndView addStudent(@PathVariable("studentId") int studentId,
			HttpServletRequest request,
			HttpSession session) {
		ModelAndView model=new ModelAndView();

		Student student=studentDao.getStudentById(studentId);
		model.addObject("student",student);
		
		int courseId=(Integer) session.getAttribute("courseId");
		model.addObject("course",courseDao.getCourseById(courseId));
		
		Transaction transaction=new Transaction(studentId,courseId);
		model.addObject("transactionDetail",transaction);
		
		model.setViewName("processForm");
		return model;
	}
	
	@PostMapping("/addStudent/{studentId}")
	public ModelAndView addStudent(
			@ModelAttribute("transactionDetail") Transaction transaction,
			@PathVariable("studentId") int studentId,
			HttpSession session
			) {
		System.out.println(transaction);
		
		Message message;
		ModelAndView modelAndView=new ModelAndView();
		int transactionId=studentDao.addTransaction(transaction);
		
		if(transactionId==-1) {
			message=new Message("Transaction failed", "danger");
			System.out.println("Transaction failed!");
			modelAndView.setViewName("admindashboard");
		}
		else {
			message=new Message("Transaction successful", "success");
			System.out.println("Transaction success!");
			modelAndView.addObject("student",studentDao.getStudentById(studentId));
			modelAndView.addObject("transaction",transaction);
			modelAndView.setViewName("viewReciept");
		}
		session.setAttribute("message", message);
		return modelAndView;
	}
	
	@GetMapping("/followUpUser/addStudent")
	public String addStudentFromFollowUp() {
		return "redirect:/addStudent";
	}
	
	@GetMapping("/creative-corner")
	public ModelAndView manageCreativeCorner() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manageCreativeCorner");
		modelAndView.addObject("courses",courseDao.getAllCoursesHavingYtLinks());
		modelAndView.addObject("links",courseDao.getAllYtLinksForCreativeCorner());
		return modelAndView;
	}
	
	@GetMapping("/addvideos")
	public ModelAndView addVideoByCourse() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("addVideosCreativeCorner");
		modelAndView.addObject("courses",courseDao.getAllCourses());
		modelAndView.addObject("link",new CreativeCornerModel());
		return modelAndView;
	}
	
	@PostMapping("/addvideos")
	public String addVideoByCourse(
			@ModelAttribute("link") CreativeCornerModel model,
			HttpSession session
			) {
			Message message;
			if(courseDao.saveYtLinkToDB(model)) {
				message=new Message("Video added,Add more!", "success");
			}
			else {
				message=new Message("Something wrong happened,Try again!", "danger");
			}
			session.setAttribute("message", message);
			
			return "redirect:/addvideos";
			
	}
	
	@GetMapping("/manageAlbums")
	public ModelAndView manageAlbums(
			HttpSession session
			) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("viewAndManageAlbums");
		List<Album> albums=galleryDao.getAllAlbums();
		if(albums!=null) {
			modelAndView.addObject("albums",albums);
		}
		else {
			Message message=new Message("No albums found! Add some albums.", "warning");
			session.setAttribute("message", message);
		}
		return modelAndView;
	}
	
	@GetMapping("/addAlbum")
	public ModelAndView addAlbum() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("addAlbum");
		modelAndView.addObject("album",new Album());
		return modelAndView;
	}
	
	@PostMapping("/addAlbum")
	public String addAlbum(
			@ModelAttribute("album") Album album,
			HttpSession session
			) {
		Message message;
		if(galleryDao.addAlbum(album)) {
			message=new Message("Album added succesfully!", "success");
		}
		else {
			message=new Message("Couldn't add album! Try later", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/manageAlbums";
	}
	
	@GetMapping("/deleteAlbum/{albumId}")
	public String deleteAlbum(
				@PathVariable("albumId") int albumId,
				HttpSession session
			) {
		Message message;
		if(galleryDao.deleteAlbumById(albumId)) {
			message=new Message("Album deleted succesfully!", "success");
		}
		else {
			message=new Message("Couldn't delete album! Try later", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/manageAlbums";
	}
	
	@GetMapping("/manageAlbumImages/{albumId}")
	public ModelAndView viewAndManageAlbumImages(
			@PathVariable("albumId") int albumId,
			HttpSession session
			) {
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manageAlbum");
		
		List<String> images=galleryDao.getImagesByAlbum(albumId);
		if(images==null) {
			Message message;
			message=new Message("No images found for this album! Add some images.", "warning");
			session.setAttribute("message", message);
		}
		else {
			modelAndView.addObject("images",images);
			modelAndView.addObject("albumId",albumId);
		}
		return modelAndView;
		
	}
	
	@GetMapping("/addImageToAlbum/{albumId}")
	public ModelAndView addImageToAlbum(
			@PathVariable("albumId") int albumId,
			HttpSession session
			) {
		ModelAndView modelAndView=new ModelAndView();
		
		
		Album album=galleryDao.getAlbumById(albumId);
		
		if(album==null) {
			session.setAttribute("message", new Message("Invalid request!", "danger"));
			modelAndView.setViewName("manageAlbum");
		}
		else {
			modelAndView.addObject("album",album);
			modelAndView.setViewName("addImage");
		}
		return modelAndView;
	}
	
	@PostMapping("/addImageToAlbum/{albumId}")
	public String addImageToAlbum(
			@PathVariable("albumId") int albumId,
			@RequestParam("imageData") MultipartFile image,
			HttpSession session
			) {
		Message message;
		if(galleryDao.addImageToAlbum(image, albumId, session)) {
			message=new Message("Image added successfully", "success");
		}
		else {
			message=new Message("Something went wrong,Try again!", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/manageAlbums";
	}
	
}
