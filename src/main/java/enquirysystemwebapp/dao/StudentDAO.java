package enquirysystemwebapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import enquirysystemwebapp.models.Student;
import enquirysystemwebapp.models.Transaction;

public interface StudentDAO {
	public int addStudent(Student student,MultipartFile profilePhoto,MultipartFile signature,HttpSession session);
    public void updateStudent(Student student);
    public boolean deleteStudent(int studentId);
    public Student getStudentById(int studentId);
    public List<Student> getStudentsByCourseId(int courseId);
    public int addTransaction(Transaction transaction);
    public Transaction getTransactionById(int transactionId);
}
