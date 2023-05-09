package enquirysystemwebapp.dao_impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import enquirysystemwebapp.dao.StudentDAO;
import enquirysystemwebapp.helper.ConnectionProvider;
import enquirysystemwebapp.models.Student;
import enquirysystemwebapp.models.Transaction;

public class StudentDAOImpl implements StudentDAO {
	
	private static Connection con;
	static
	{
		con=ConnectionProvider.getConnection();
	}
	
	
	public int addStudent(Student student,MultipartFile profilePhoto,MultipartFile signature,HttpSession session) {
		// TODO Auto-generated method stub
		try {
			byte[] userImage=profilePhoto.getBytes();
			byte[] userSignature=signature.getBytes();
			
			String path;
			//Writing Profile Photo
			path=session.getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+profilePhoto.getOriginalFilename();
			FileOutputStream fos=new FileOutputStream(path); 
			fos.write(userImage);
			fos.close(); 
			System.out.println(path);
			//Writing Signature
			path=session.getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+signature.getOriginalFilename();
			fos=new FileOutputStream(path); 
			fos.write(userSignature);
			fos.close(); 
			System.out.println(path);
			
			student.setProfilePhoto(profilePhoto.getOriginalFilename());
			student.setSignature(signature.getOriginalFilename());
			
			try {
				String query="insert into students(studentName,fatherName,motherName,dateOfBirth,gender,residentialAddress,officeAddress,residentialPhone,officePhone,mobile,email,image,signature,qualification)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement pst=con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setString(1, student.getStudentName());
				pst.setString(2, student.getFatherName());
				pst.setString(3, student.getMotherName());
				pst.setString(4, student.getDateOfBirth());
				pst.setString(5, student.getGender());
				pst.setString(6, student.getResidentialAddress());
				pst.setString(7, student.getOfficeAddress());
				pst.setString(8, student.getResidentialPhone());
				pst.setString(9, student.getOfficePhone());
				pst.setString(10, student.getMobile());
				pst.setString(11, student.getEmail());
				pst.setString(12, student.getProfilePhoto());
				pst.setString(13, student.getSignature());
				pst.setString(14, student.getEducationalQualification());
				
				int affectedRows=pst.executeUpdate();
				
				if(affectedRows>0) {
					ResultSet generatedKeys = pst.getGeneratedKeys();
			        if (generatedKeys.next()) {
			            return generatedKeys.getInt(1); // get the generated ID value
			        }
				}
				
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return -1;
	}

	public void updateStudent(Student student) {
		// TODO Auto-generated method stub

	}

	public boolean deleteStudent(int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	public Student getStudentById(int studentId) {
		// TODO Auto-generated method stub
		try {
			String query="select * from students where studentId=?";
			
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, studentId);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Student student=new Student();
				student.setStudentId(rs.getInt(1));
				student.setStudentName(rs.getString(16));
				student.setFatherName(rs.getString(2));
				student.setMotherName(rs.getString(3));
				student.setDateOfBirth(rs.getString(4));
				student.setGender(rs.getString(5));
				student.setResidentialAddress(rs.getString(6));
				student.setOfficeAddress(rs.getString(7));
				student.setResidentialPhone(rs.getString(8));
				student.setOfficePhone(rs.getString(9));
				student.setMobile(rs.getString(10));
				student.setEmail(rs.getString(11));
				student.setDateOfRegistration(rs.getString(12));
				student.setProfilePhoto(rs.getString(13));
				student.setSignature(rs.getString(14));
				student.setEducationalQualification(rs.getString(15));
				
				return student;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public Student getStudentPrimaryDetails(int studentId) {
		// TODO Auto-generated method stub
		try {
			String query="select studentName, fatherName, mobile, email from students where studentId=?";
			
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, studentId);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Student student=new Student();
				student.setStudentName(rs.getString(16));
				student.setFatherName(rs.getString(2));
				student.setMobile(rs.getString(10));
				student.setEmail(rs.getString(11));
				
				return student;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public List<Student> getStudentsByCourseId(int courseId) {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		try {
			String query="select * from students";
			
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Student student=new Student();
				student.setStudentId(rs.getInt(1));
				student.setStudentName(rs.getString(16));
				student.setFatherName(rs.getString(2));
				student.setMotherName(rs.getString(3));
				student.setDateOfBirth(rs.getString(4));
				student.setGender(rs.getString(5));
				student.setResidentialAddress(rs.getString(6));
				student.setOfficeAddress(rs.getString(7));
				student.setResidentialPhone(rs.getString(8));
				student.setOfficePhone(rs.getString(9));
				student.setMobile(rs.getString(10));
				student.setEmail(rs.getString(11));
				student.setDateOfRegistration(rs.getString(12));
				student.setProfilePhoto(rs.getString(13));
				student.setSignature(rs.getString(14));
				student.setEducationalQualification(rs.getString(15));
				
				students.add(student);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return students;
	}
	
	public int addTransaction(Transaction transaction) {
		try {
			String query="insert into student_transactions(studentId,courseId,chequeNo,bankName,mode) values(?,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			pst.setInt(1, transaction.getStudentId());
			pst.setInt(2, transaction.getCourseId());
			pst.setString(3, transaction.getChequeNo());
			pst.setString(4, transaction.getBankName());
			pst.setString(5, transaction.getMode());
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				ResultSet generatedKeys = pst.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            return generatedKeys.getInt(1); // get the generated ID value
		        }
			}
			
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return -1;
	}
	
	public Transaction getTransactionById(int transactionId) {
		try {
			String query="select * from student_transactions where transactionId=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, transactionId);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Transaction transaction=new Transaction();
				
				transaction.setStudentId(rs.getInt(1));
				transaction.setCourseId(rs.getInt(2));
				transaction.setChequeNo(rs.getString(3));
				transaction.setBankName(rs.getString(4));
				transaction.setTransactionDate(rs.getString(5));
				transaction.setTransactionId(6);
				transaction.setMode(rs.getString(7));
				return transaction;
			}			
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

}
