package enquirysystemwebapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.CreativeCornerModel;



public interface CourseDAO {
	public int addCourse(Course course,HttpSession session);
	public boolean updateCourse(Course course,HttpSession session);
	public void deleteCourse(int courseId);
	public Course getCourseById(int courseId);
	public List<Course> getAllCourses();
	public List<Course> getPastCourses();
	public List<Course> getUpcomingCourses();
	public List<Course> getCurrentMonthCourses();
	//used for schedule-layout (rowspan)
	public int getNumberOfDaysOfCourseByCourseId(int courseId);
	
	public List<String> getYtLinksForCreativeCornerByCourseId(int courseId);
	public List<CreativeCornerModel> getAllYtLinksForCreativeCorner();
	public boolean saveYtLinkToDB(CreativeCornerModel model);
	public List<Course> getAllCoursesHavingYtLinks();
	public boolean deleteVideo(int linkId);
}
