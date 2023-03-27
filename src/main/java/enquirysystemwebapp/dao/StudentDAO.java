package enquirysystemwebapp.dao;

import java.util.List;

import enquirysystemwebapp.models.Student;

public interface StudentDAO {
	public boolean addStudent(Student student);
    public void updateStudent(Student student);
    public boolean deleteStudent(int studentId);
    public Student getStudentById(int studentId);
    public List<Student> getStudentsByCourseId(int courseId);
}
