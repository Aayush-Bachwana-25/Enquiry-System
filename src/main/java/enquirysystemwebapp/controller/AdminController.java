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
	
	@GetMapping("/admin/addcourse")
	public ModelAndView addNewCourse(){
		Course course=new Course();
		return new ModelAndView("addcourse","nc",course);
	}

	@PostMapping("/admin/addcourse")
	public String postaddNewCourse(@ModelAttribute("nc") Course course,
			HttpSession session
			){	
		
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
		
		return "redirect:/admin/addBatch/"+course.getCourseId()+"/"+course.getCourseName();
	}
	
	@GetMapping("/admin/managecourses")
	public ModelAndView manageCourses() {
		ModelAndView model=new ModelAndView();
		model.setViewName("manageCourses");
		model.addObject("title", "Manage Courses");
		model.addObject("courses",courseDao.getAllCourses());
		return model;
	}
	
	@GetMapping("/admin/delete/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int courseId) {
		courseDao.deleteCourse(courseId);
		return "redirect:/admin/managecourses";
	}
	
	@GetMapping("/admin/editCourse/{courseId}")
	public ModelAndView editCourse(@PathVariable("courseId") int courseId) {
		ModelAndView model=new ModelAndView();
		model.setViewName("modifyCourse");
		model.addObject("title", "Edit Course");
		model.addObject("course",courseDao.getCourseById(courseId));
		return model;	
	}
	
	@PostMapping("/admin/editCourse/{courseId}")
	public String editCourse(
			@ModelAttribute Course course,
			HttpSession session
			) {
		Message message;
		if(courseDao.updateCourse(course, session)) {
			message=new Message("Course updated successfully!", "success");
		}
		else {
			message=new Message("Couldn't update course, Try later!", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/admin/managecourses";
	}
	
	@GetMapping("/admin/addBatch/{courseId}/{courseName}")
	public ModelAndView addBatchForCourse(
			@PathVariable("courseId") int courseId,
			@PathVariable("courseName") String courseName,
			HttpServletRequest request
			) {
		Batch batch=new Batch(courseId);
		ModelAndView model=new ModelAndView();
		model.setViewName("/addBatch");
		model.addObject("ns",batch);
		request.setAttribute("courseName",courseName);
		return model;
	}
	
	@PostMapping("/admin/addBatch/{courseId}/{courseName}")
    public String addBatchForCourse(@ModelAttribute("ns") Batch batch,
    		@PathVariable("courseId") int courseId,
    		@PathVariable("courseName") String courseName,
    		HttpSession session
    		) {
		
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
    	return "redirect:/admin/managebatches";
    }
	
	@GetMapping("/admin/managebatches")
	public ModelAndView showCoursesForManagingBatches() {
		ModelAndView model=new ModelAndView();
		model.setViewName("manageBatches");
		
		List<Course> courses= courseDao.getAllCourses();
		model.addObject("courses",courses);
		return model;
	}
	
	@GetMapping("/admin/manageBatchByCourse/{courseId}/")
	public ModelAndView manageBatchByCourse(
			@PathVariable("courseId") int courseId
			) {
		List<Batch> batches=batchDao.getBatchesByCourseId(courseId);
		Course course=courseDao.getCourseById(courseId);
		ModelAndView model=new ModelAndView();
		model.setViewName("manageBatchesForCourse");
		model.addObject("batches",batches);
		model.addObject("course",course);
		model.addObject("title","Batches: "+course.getCourseName());	
		return model;
	}
	
	@GetMapping("/admin/manageBatchByCourse/{courseId}/deleteBatch/{id}")
	public String deleteBatchForCourse(@PathVariable("id") int batchId,
			@PathVariable("courseId") int courseId,
			HttpSession session
			) {
		Message message;
		if(batchDao.deleteBatch(batchId)) {
			System.out.println("Batch deleted");
			message=new Message("Batch deleted successfully!", "success");
		}
		else {
			message=new Message("Something went wrong,Try again!", "danger");
		}
		session.setAttribute("message",message);
		return "redirect:/admin/manageBatchByCourse/"+courseId+"/";
	}
	
	@GetMapping("/admin/manageBatchByCourse/{courseId}/editBatch/{id}")
	public ModelAndView editBatchForCourse(@PathVariable("id") int batchId,
			@PathVariable("courseId") int courseId,
			HttpSession session) {

		ModelAndView model=new ModelAndView();
		
		Batch batch=batchDao.getBatchById(batchId);
		if(batch!=null) {
			model.setViewName("editBatch");
			model.addObject("batch",batch);		}
		else {
			model.setViewName("redirect:/admin/manageBatchByCourse/"+courseId+"/");
		}
		return model;
	}
	
	@PostMapping("/admin/manageBatchByCourse/{courseId}/editBatch/{id}")
	public String editBatchForCourse(@ModelAttribute("batch") Batch batch,
			HttpSession session) {
		Message message;
		
		if(batchDao.updateBatch(batch)) {
			message=new Message("Batch updated succesfully!", "success");
		}
		else {
			message=new Message("Something went wrong!", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/admin/manageBatchByCourse/"+batch.getCourseId()+"/";
		
	}
	
	@GetMapping("/admin/viewqueries")
	public ModelAndView viewAndManageQueries(
			HttpSession session
			) {
		ModelAndView model=new ModelAndView();
		model.setViewName("viewQueries");
		
		List<Query> enquiries=enquiryDao.getAllQueriesForToday();
		if(enquiries==null) {
			session.setAttribute("message",new Message("No queries for today!", "secondary"));
		}
		else {
			model.addObject("enquiries",enquiries);
			model.addObject("title","Today's Queries");
		}
		return model;
	}
	
	@GetMapping("/admin/followUpUser/{id}")
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
	
	@PostMapping("/admin/followUpUser/saveFollowUp")
	public RedirectView saveFollowUp(@RequestParam("followDate") String updatedFollowDate,
			@RequestParam("feedback") String feedback,
			HttpServletRequest request,
			HttpSession session) {
		
		
		Message message;
		
		int enquiryId=(Integer)session.getAttribute("enquiryId");
		
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
		redirectView.setUrl(request.getContextPath()+"/admin/followUpUser/"+enquiryId);
		session.removeAttribute("enquiryId");
		return redirectView;
	}
	
	
	@GetMapping("/admin/addStudent")
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
	
	@PostMapping("/admin/addStudent")
	public String addStudent(@ModelAttribute("student") Student student,
			@RequestParam("profile") MultipartFile profilePhoto,
			@RequestParam("sign") MultipartFile signature,
			@RequestParam("courseId") int courseId,
			HttpSession session) {
		
		Message message;
		String url;

		int studentId=studentDao.addStudent(student,profilePhoto,signature,session);
		if(studentId==-1) {
			url="redirect:/admin/addStudent";
			message=new Message("Something went wrong", "danger");
			System.out.println("Registration failed!");
		}
		else{
			session.setAttribute("courseId", courseId);
			url="redirect:/admin/addStudent/"+studentId;
			message=new Message("Registration successful", "success");
			System.out.println("Student registered!");
		}
		session.setAttribute("message", message);
		return url;
	}
	
	@GetMapping("/admin/addStudent/{studentId}")
	public ModelAndView addStudent(@PathVariable("studentId") int studentId,
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
	
	@PostMapping("/admin/addStudent/{studentId}")
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
			session.setAttribute("message", message);
			studentDao.deleteStudent(studentId);
			System.out.println("Transaction failed!");
			modelAndView.setViewName("admindashboard");
		}
		else {
			transaction.setTransactionId(transactionId);
			modelAndView.addObject("student",studentDao.getStudentById(transaction.getStudentId()));
			modelAndView.addObject("course", courseDao.getCourseById(transaction.getCourseId()));
			modelAndView.addObject("transaction",transaction);
			modelAndView.setViewName("printreciept");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/admin/followUpUser/addStudent")
	public String addStudentFromFollowUp() {
		return "redirect:/admin/addStudent";
	}
	
	@GetMapping("/admin/creative-corner")
	public ModelAndView manageCreativeCorner() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manageCreativeCorner");
		modelAndView.addObject("courses",courseDao.getAllCoursesHavingYtLinks());
		modelAndView.addObject("links",courseDao.getAllYtLinksForCreativeCorner());
		return modelAndView;
	}
	
	@GetMapping("/admin/addvideos")
	public ModelAndView addVideoByCourse() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("addVideosCreativeCorner");
		modelAndView.addObject("courses",courseDao.getAllCourses());
		modelAndView.addObject("link",new CreativeCornerModel());
		return modelAndView;
	}
	
	@PostMapping("/admin/addvideos")
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
			
			return "redirect:/admin/addvideos";
			
	}
	
	@GetMapping("/admin/deleteVideo/{linkId}")
	public String deleteVideoForCreativeCorner(
			@PathVariable("linkId") int linkId,
			HttpSession session
			) {
			Message message;
			if(courseDao.deleteVideo(linkId)) {
				message=new Message("Video removed!", "success");
			}
			else {
				message=new Message("Couldn't delete video, Try later!", "danger");
			}
			session.setAttribute("message", message);
			return "redirect:/admin/creative-corner";
	}
	
	

	@GetMapping("/admin/manageAlbums")
	public ModelAndView manageAlbums(
			HttpSession session
			) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("gallery");
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
	
	@GetMapping("/admin/addAlbum")
	public ModelAndView addAlbum() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("addAlbum");
		modelAndView.addObject("album",new Album());
		return modelAndView;
	}
	
	@PostMapping("/admin/addAlbum")
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
		return "redirect:/admin/manageAlbums";
	}
	
	@GetMapping("/admin/deleteAlbum/{albumId}")
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
		return "redirect:/admin/manageAlbums";
	}
	
	@GetMapping("/admin/manageAlbumImages/{albumId}")
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
			message=new Message("No images found for this album! Add some images.", "warning");
			session.setAttribute("message", message);
		}
		else {
			modelAndView.addObject("images",images);
		}
		return modelAndView;
		
	}
	
	@GetMapping("/admin/addImageToAlbum/{albumId}")
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
	
	@PostMapping("/admin/addImageToAlbum/{albumId}")
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
		return "redirect:/admin/manageAlbumImages/"+albumId;
	}
	
	@GetMapping("/admin/manageAlbumImages/deleteImage/{albumId}/{fileName}.{fileFormat}")
	public String deleteImageFromAlbum(
			@PathVariable("albumId") int albumId,
			@PathVariable("fileName") String fileName,
			@PathVariable("fileFormat") String fileFormat,
			HttpSession session
			) {
		
		Message message;
		
		if(galleryDao.deleteImageFromAlbum(albumId,fileName,fileFormat,session)) {
			message=new Message("Image deleted", "success");
		}
		else {
			message=new Message("Something went wrong!", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/admin/manageAlbumImages/"+albumId;
	}
	
	@GetMapping("/admin/manageAlbumImages/setImageAsCover/{albumId}/{fileName}.{fileFormat}")
	public String setAlbumCover(
			@PathVariable("albumId") int albumId,
			@PathVariable("fileName") String fileName,
			@PathVariable("fileFormat") String fileFormat,
			HttpSession session
			) {
		
		Message message;
		
		if(galleryDao.setImageAsAlbumCover(albumId,fileName,fileFormat)) {
			message=new Message("Image set as album cover photo!", "success");
		}
		else {
			message=new Message("Something went wrong!", "danger");
		}
		session.setAttribute("message", message);
		return "redirect:/admin/manageAlbumImages/"+albumId;
	}
	
	
	
}
