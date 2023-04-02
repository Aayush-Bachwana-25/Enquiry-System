package enquirysystemwebapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.CreativeCornerModel;



public interface CourseDAO {
	public int addCourse(Course course,HttpSession session);
	public void updateCourse(Course course,HttpSession session);
	public void deleteCourse(int courseId);
	public Course getCourseById(int courseId);
	public List<Course> getAllCourses();
	public int getNumberOfDaysOfCourseByCourseId(int courseId);
	
	public List<String> getYtLinksForCreativeCornerByCourseId(int courseId);
	public List<CreativeCornerModel> getAllYtLinksForCreativeCorner();
	public boolean saveYtLinkToDB(CreativeCornerModel model);
	public List<Course> getAllCoursesHavingYtLinks();
}
