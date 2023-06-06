package com.students.service;

import java.util.List;

import com.students.dao.StudentDao;
import com.students.dao.StudentDaoImpl;
import com.students.model.Student;

public class StudentServiceImpl implements StudentService {
		
	private StudentDao studentDao;
	
	public StudentServiceImpl(){
		studentDao = new StudentDaoImpl();
	}

	public List<Student> getStudents() 
	{
		return studentDao.getStudents();
	}

	@Override
	public int addStudent(Student s) {
		return studentDao.addStudent(s);
	}

	@Override
	public Student getStudent(Long id) {
		return studentDao.getStudent(id);
	}

	@Override
	public int updateStudent(Student s) {
		return studentDao.updateStudent(s);
	}

	@Override
	public int deleteStudent(Long id) {
		return studentDao.deleteStudent(id);
	}

	@Override
	public List<Student> searchStudent(Student student) {
		return studentDao.searchStudents(student);
	}
}
