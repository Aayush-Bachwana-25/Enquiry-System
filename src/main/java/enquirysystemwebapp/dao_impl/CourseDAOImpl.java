package enquirysystemwebapp.dao_impl;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import enquirysystemwebapp.dao.CourseDAO;
import enquirysystemwebapp.helper.ConnectionProvider;
import enquirysystemwebapp.models.Course;
import enquirysystemwebapp.models.CreativeCornerModel;



public class CourseDAOImpl implements CourseDAO{

	@Autowired
	private Course course;
	
	
	private static Connection con;
	
	static
	{
		con=ConnectionProvider.getConnection();
	}
	
	
	public CourseDAOImpl() {}
	
	public CourseDAOImpl(Course course) {
		super();
		this.course = course;
	}
	
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int addCourse(Course course,HttpSession session) {
		// TODO Auto-generated method stub
		try {
			MultipartFile file=course.getImageData();
			byte[] data=file.getBytes();
			course.setImage(file.getOriginalFilename());
			String path=session.getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+file.getOriginalFilename();
		  
			FileOutputStream fos=new FileOutputStream(path); 
			fos.write(data);
			fos.close(); 
			System.out.println(path);
		
		
			String insertCourse="INSERT INTO COURSES(course_name,age_group,no_of_hrs,start_date,end_date,fees,about,course_img) VALUES(?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst=con.prepareStatement(insertCourse,PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, course.getCourseName());
			pst.setString(2, course.getAgeGroup());
			pst.setInt(3, course.getNoOfHrs());
			pst.setString(4, course.getStartDate());
			pst.setString(5, course.getEndDate());
			pst.setInt(6, course.getFees());
			pst.setString(7, course.getAbout());
			pst.setString(8, course.getImage());
			int affectedRows=pst.executeUpdate();
			
			if (affectedRows == 0) {
		        throw new SQLException("Inserting course failed, no rows affected.");
		    }	
			try{
				ResultSet generatedKeys = pst.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            int id = generatedKeys.getInt(1); // get the generated ID value
		            return id;
		        
		        } 
		        else {
		            throw new SQLException("Inserting course failed, no ID obtained.");
		        }
		    }
			catch(SQLException ee) {
				ee.printStackTrace();
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		
		
		return -1;
		
	}

	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		List<Course> list=new ArrayList<Course>();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from courses");
			
			while(rs.next()) {
				Course course=new Course();
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setAgeGroup(rs.getString(3));
				course.setNoOfHrs(rs.getInt(4));
				course.setStartDate(rs.getDate(5).toString());
				course.setEndDate(rs.getDate(6).toString());
				course.setFees(rs.getInt(7));
				course.setAbout(rs.getString(8));
				course.setImage(rs.getString(9));
				
				list.add(course);	
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return list;
	}
	
	public void updateCourse(Course course,HttpSession session) {
		// TODO Auto-generated method stub
		try {
			MultipartFile file=course.getImageData();
			byte[] data=file.getBytes();
			course.setImage(file.getOriginalFilename());
			String path=session.getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+file.getOriginalFilename();
		  
			FileOutputStream fos=new FileOutputStream(path); 
			fos.write(data);
			fos.close(); 
			System.out.println(path);
			
			String updateCourseQuery="UPDATE COURSES SET course_name=?, age_group=? ,no_of_hrs=? ,start_date=? ,end_date=?,fees=?,about=?,course_img=? where course_id=?";
			
			PreparedStatement pst=con.prepareStatement(updateCourseQuery);
			pst.setString(1, course.getCourseName());
			pst.setString(2, course.getAgeGroup());
			pst.setInt(3, course.getNoOfHrs());
			pst.setString(4, course.getStartDate());
			pst.setString(5, course.getEndDate());
			pst.setInt(6, course.getFees());
			pst.setString(7, course.getAbout());
			pst.setString(8, course.getImage());
			pst.setInt(9, course.getCourseId());
			pst.execute();
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		
	}

	public void deleteCourse(int courseId) {
		// TODO Auto-generated method stub
		
		try {
			String query="DELETE from COURSES where course_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, courseId);
			pst.execute();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public Course getCourseById(int courseId) {
		// TODO Auto-generated method stub
		Course course;
		try {
			
			String query="SELECT * from COURSES where course_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, courseId);
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()) {
				course=new Course();
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setAgeGroup(rs.getString(3));
				course.setNoOfHrs(rs.getInt(4));
				course.setStartDate(rs.getDate(5).toString());
				course.setEndDate(rs.getDate(6).toString());
				course.setFees(rs.getInt(7));
				course.setAbout(rs.getString(8));
				course.setImage(rs.getString(9));
				return course;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfDaysOfCourseByCourseId(int courseId) {
		// TODO Auto-generated method stub
		try {
			String query="select DATEDIFF(end_date, start_date) from courses where course_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, courseId);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Course> getAllCoursesHavingYtLinks(){
		List<Course> list=new ArrayList<Course>();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from courses where course_id in (select course_id from creative_corner)");
			
			while(rs.next()) {
				Course course=new Course();
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setAgeGroup(rs.getString(3));
				course.setNoOfHrs(rs.getInt(4));
				course.setStartDate(rs.getDate(5).toString());
				course.setEndDate(rs.getDate(6).toString());
				course.setFees(rs.getInt(7));
				course.setAbout(rs.getString(8));
				course.setImage(rs.getString(9));
				
				list.add(course);	
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return list;
	}
	
	public List<String> getYtLinksForCreativeCornerByCourseId(int courseId) {
		// TODO Auto-generated method stub
		try {
			//Select latest 8 videos
			String query="select link from creative_corner where course_id=?  order by link_id desc limit 8";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, courseId);
			ResultSet rs=pst.executeQuery();
			
			List<String> links=new ArrayList<String>();
			
			while(rs.next()) {
				String url=rs.getString(1);
				String videoCode = "";
				String[] urlParams = url.split("&");
				for (String param : urlParams) {
				    if (param.contains("v=")) {
				        videoCode = param.substring(param.indexOf("v=")+2);
				        System.out.println(videoCode);
				        break;
				    }
				}
				
				links.add(videoCode);
			}
			
			return (links.size()>0) ? links:  null;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CreativeCornerModel> getAllYtLinksForCreativeCorner() {
		// TODO Auto-generated method stub
		try {
			//Select latest 8 videos
			String query="select link_id,course_id,link from creative_corner";
			PreparedStatement pst=con.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			
			List<CreativeCornerModel> links=new ArrayList<CreativeCornerModel>();
			
			while(rs.next()) {
				CreativeCornerModel model=new CreativeCornerModel();
				model.setLinkId(rs.getInt(1));
				model.setCourseId(rs.getInt(2));
				model.setUrl(rs.getString(3));
				links.add(model);
			}
			return links;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveYtLinkToDB(CreativeCornerModel model) {
		// TODO Auto-generated method stub
		try {
			String query="insert into creative_corner(course_id,link) values(?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, model.getCourseId());
			pst.setString(2, model.getUrl());
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				return true;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
}
