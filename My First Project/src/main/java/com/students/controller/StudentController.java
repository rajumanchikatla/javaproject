package com.students.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.students.common.exception.DuplicateStudentException;
import com.students.model.Student;
import com.students.service.StudentService;
import com.students.service.StudentServiceImpl;

/**
 * Servlet implementation class StudentController
 */
public class StudentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger LOGGER = Logger.getLogger(StudentController.class.getName());  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	StudentService studentService = null;
	
    public StudentController() {
        super();
        studentService = new StudentServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subAction = request.getParameter("subAction");
		String SUCCESS_VIEW = null;
		List<String> errors = new ArrayList<String>();
		List<String> successMessages = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("successMessages", successMessages);
		
		LOGGER.info("Inside of doPost() method of Student Controller subAction: -> "+subAction);
		if("LIST_STUDENT".equals(subAction)) {
			SUCCESS_VIEW = getStudent(request);
		}else if("ADD_STUDENT".equals(subAction)){
			SUCCESS_VIEW = "listStudent/addStudent.jsp";
		}else if("ADD_SUBMIT".equals(subAction)) {
			Student s = new Student();
			s.setId(Long.valueOf(request.getParameter("Student_id")));
			s.setFirstName(request.getParameter("firstName"));
			s.setLastName(request.getParameter("lastName"));
			s.setGrade(request.getParameter("grade"));
			s.setEmergencyContactNumber(Long.valueOf(request.getParameter("Emergeyno")));
			try {
				studentService.addStudent(s);
				successMessages.add("Student "+s.getId()+" succesfully added to system");
				SUCCESS_VIEW = getStudent(request);
			}catch(DuplicateStudentException ex) {
				SUCCESS_VIEW = "listStudent/addStudent.jsp";
				errors.add("BE_101 - Student Id "+ s.getId()+" already present in System");
				LOGGER.info("BE_101 - Student Id "+ s.getId()+" already present in System");
			}catch(Exception ex) {
				SUCCESS_VIEW = "listStudent/addStudent.jsp";
				errors.add("Some error occured");
				LOGGER.info("Some error occured While finding the student"+ s.getId());
			}
		}else if("UPDATE".equals(subAction)) {
			Long studentId = Long.valueOf(request.getParameter("student_id"));
			Student s = studentService.getStudent(studentId);
			request.setAttribute("student", s);
			SUCCESS_VIEW = "listStudent/modifyStudent.jsp";
		}else if("MODIFY_SUBMIT".equals(subAction)) {
			Student s = new Student();
			s.setId(Long.valueOf(request.getParameter("Student_id")));
			s.setFirstName(request.getParameter("firstName"));
			s.setLastName(request.getParameter("lastName"));
			s.setGrade(request.getParameter("grade"));
			s.setEmergencyContactNumber(Long.valueOf(request.getParameter("Emergeyno")));
			
			try {
				studentService.updateStudent(s);
				successMessages.add("student "+s.getId()+" updated succesfully");
				SUCCESS_VIEW=getStudent(request);
			}catch(Exception ex) {
				LOGGER.error("Some error While updating Student Details. "+ s.getId());
			}
		}else if("DELETE".equals(subAction)) {
			Long studentId = Long.valueOf(request.getParameter("student_id"));
			try {
				int deleteCount = studentService.deleteStudent(studentId);
				if(deleteCount == 1) {
					successMessages.add("Student "+studentId+" succesfully deleted from system");
					successMessages.add("A total of "+deleteCount+" students deleted");
					SUCCESS_VIEW = getStudent(request);
				}
			}catch(Exception ex) {
				LOGGER.error("Some error occured While deleting the student"+ studentId);
			}
		}else if("SEARCH_SCREEN".equals(subAction)){
			SUCCESS_VIEW = "listStudent/searchStudents.jsp";
		}else if("SEARCH_SUBMIT".equals(subAction)) {
			Student s = new Student();
			if(!request.getParameter("Student_id").trim().isEmpty())
			s.setId(Long.valueOf(request.getParameter("Student_id")));
			s.setFirstName(request.getParameter("firstName"));
			s.setLastName(request.getParameter("lastName"));
			s.setGrade(request.getParameter("grade"));
			if(s.isValidForSearch()) {
				List<Student> students = studentService.searchStudent(s);
				if(students.size() == 0)
				errors.add("BE_102 - No Studnts Found for given Criteria !");
				request.setAttribute("students", students);
				SUCCESS_VIEW = "listStudent/students.jsp";
			}else {
				errors.add("BE_103 kindly provie atleast one field !..");
				SUCCESS_VIEW = "listStudent/searchStudents.jsp";
			}	
		}
		RequestDispatcher view = request.getRequestDispatcher(SUCCESS_VIEW);
		LOGGER.info("End of doPost() method of Student Controller. SuccessView -> "+SUCCESS_VIEW);
		view.forward(request, response);
	}

	private String getStudent(HttpServletRequest request) {
		List<Student> students = studentService.getStudents();
		request.setAttribute("students", students);
		return "listStudent/students.jsp";
	}
}
