package com.students.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.students.common.exception.DuplicateStudentException;
import com.students.contstants.SQLConstants;
import com.students.model.Student;
import com.students.util.DBUtil;

public class StudentDaoImpl implements StudentDao {
	
	 private DBUtil dbUtil;
	 
	 private static Logger LOGGER = Logger.getLogger(StudentDaoImpl.class.getName()); 
	 
	 public StudentDaoImpl(){
		 dbUtil = new DBUtil();
	 }

	public List<Student> getStudents() {
		LOGGER.info("Inside of getStudents() of StudentDaoImpl");
		Connection conn = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.LIST_STUDENTS);
			LOGGER.info("SQL QUERY-> "+SQLConstants.LIST_STUDENTS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getLong(1));
				s.setFirstName(rs.getString(2));
				s.setLastName(rs.getString(3));
				s.setGrade(rs.getString(4));
				s.setEmergencyContactNumber(rs.getLong(5));
				studentList.add(s);
			}
			LOGGER.info("end of getStudents() of StudentDaoImpl");
		}catch(SQLException ex) {
			LOGGER.error("Some occure while fetching student data: "+ex.getMessage());
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		return studentList;
	}


	public int addStudent(Student s) {
		int count = -1;
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_QUERY);
			LOGGER.info("SQL QUERY-> "+SQLConstants.ADD_QUERY);
			ps.setLong(1, s.getId());
			ps.setString(2, s.getFirstName());
			ps.setString(3, s.getLastName());
			ps.setString(4, s.getGrade());
			ps.setLong(5, s.getEmergencyContactNumber());
			count = ps.executeUpdate();
		}catch(MySQLIntegrityConstraintViolationException ex){
			LOGGER.error("Student Id present in System : "+ex.getMessage());
			throw new DuplicateStudentException();
		}catch(SQLException ex) {
			LOGGER.error("Some occure while fetching student data: "+s.getId()+" "+ex.getMessage());;
			System.out.println("Failed to fetch logs");
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return count;
	}

	public Student getStudent(Long id) {
		Connection conn = null;
		Student student = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_STUDENT);
			LOGGER.info("SQL Query -> "+SQLConstants.GET_STUDENT);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getLong(1));
				student.setFirstName(rs.getString(2));
				student.setLastName(rs.getString(3));
				student.setGrade(rs.getString(4));
				student.setEmergencyContactNumber(rs.getLong(5));
			}
		}catch(SQLException ex) {
			LOGGER.error("Error occured while fetching student data: "+id+" "+ex.getMessage());
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		return student;
	}

	public int updateStudent(Student s) {
		int count = -1;
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_STUDENT);
			LOGGER.info("SQL Query -> "+SQLConstants.UPDATE_STUDENT);
			ps.setString(1, s.getFirstName());
			ps.setString(2, s.getLastName());
			ps.setString(3, s.getGrade());
			ps.setLong(4, s.getEmergencyContactNumber());
			ps.setLong(5, s.getId());
			count = ps.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed to fetch logs");
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return count;
	}

	public int deleteStudent(Long id) {
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.DELETE_STUDENT);
			LOGGER.info("SQL Query -> "+SQLConstants.DELETE_STUDENT);
			ps.setLong(1, id);
			return ps.executeUpdate();
		}catch(Exception ex) {
			LOGGER.error("Error occured while deleting student data: "+id+" "+ex.getMessage());
		}finally {
			if(conn !=null)
				dbUtil.closeConnection(conn);
		}
		
		return -1;
	}

	public List<Student> searchStudents(Student student) {
		Connection conn = null;
		List<Student> studentList = new ArrayList<Student>();
		String query = SQLConstants.SEARCH_STUDENTS;
		
		if(student.getId() != 0) 
			query += " and id like '"+student.getId()+"%'";
		if(student.getFirstName() !=null && !student.getFirstName().trim().isEmpty())
			query += " and firstName like '%"+student.getFirstName()+"%'";
		if(student.getLastName() !=null && !student.getLastName().trim().isEmpty())
			query += " and lastName like '%"+student.getLastName()+"%'";
		if(student.getGrade() !=null && !student.getGrade().trim().isEmpty())
			query += " and grade like '%"+student.getGrade()+"%'";
		
		try {
			conn = dbUtil.getConnection();
			LOGGER.info("SQL Query -> "+query);
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getLong(1));
				s.setFirstName(rs.getString(2));
				s.setLastName(rs.getString(3));
				s.setGrade(rs.getString(4));
				s.setEmergencyContactNumber(rs.getLong(5));
				studentList.add(s);
			}
		}catch(SQLException ex) {
			LOGGER.error("Error occured while Searching students data: "+ex.getMessage());
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		return studentList;
	}
	

}
