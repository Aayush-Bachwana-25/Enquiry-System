package enquirysystemwebapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import enquirysystemwebapp.models.Course;



public interface CourseDAO {
	public 	boolean addCourse(Course course,HttpSession session);
	public void updateCourse(Course course,HttpSession session);
	public void deleteCourse(int courseId);
	public Course getCourseById(int courseId);
	public List<Course> getAllCourses();
}
