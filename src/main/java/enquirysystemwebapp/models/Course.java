package enquirysystemwebapp.models;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Course {
	private int courseId;
	private String courseName;
	private String ageGroup;
	private int noOfHrs;
	private String startDate;
	private String endDate;
	private int fees;
	private String about;
	private String image;
	
	private MultipartFile imageData;
	
	
	
	public int getCourseId() {
		return courseId;
	}



	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getAgeGroup() {
		return ageGroup;
	}



	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}



	public int getNoOfHrs() {
		return noOfHrs;
	}



	public void setNoOfHrs(int noOfHrs) {
		this.noOfHrs = noOfHrs;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public int getFees() {
		return fees;
	}



	public void setFees(int fees) {
		this.fees = fees;
	}



	public String getAbout() {
		return about;
	}



	public void setAbout(String about) {
		this.about = about;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public MultipartFile getImageData() {
		return imageData;
	}



	public void setImageData(MultipartFile imageData) {
		this.imageData = imageData;
	}



	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", ageGroup=" + ageGroup + ", fees=" + fees + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
