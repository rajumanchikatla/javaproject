package com.students.service;

import java.util.List;

import com.students.model.Student;

public interface StudentService {

	public List<Student> getStudents();
	
	public int addStudent(Student s);
	
	public Student getStudent(Long id);
	
	public int updateStudent(Student s);
	
	public int deleteStudent(Long id);
	
	public List<Student> searchStudent(Student student);
}
