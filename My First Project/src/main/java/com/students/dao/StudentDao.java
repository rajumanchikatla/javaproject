package com.students.dao;

import java.util.List;

import com.students.model.Student;

public interface StudentDao {
	
	public List<Student> getStudents();
	
	public int addStudent(Student s);

	public Student getStudent(Long id);
	
	public int updateStudent(Student s);

	public int deleteStudent(Long id);
	
	public List<Student> searchStudents(Student student);
	
}
