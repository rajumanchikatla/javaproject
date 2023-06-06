<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.students.model.Student" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Students</title>
	<link rel="stylesheet" type="text/css" href="students.css">
	<%List<Student> students = (List<Student>)request.getAttribute("students"); %>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/listStudent/students.css" />	
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/nav-bar.css" />
	<%List<String> successMessages = (List<String>) request.getAttribute("successMessages"); %>
	<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
	<jsp:include page="../common/nav-bar.jspf"></jsp:include>
	<% if(successMessages!=null && successMessages.size() > 0) { %>
		<div class="success-container"> 
			<%for(String s: successMessages) {%>
	             <p class="success-message"><%=s%></p>
			<%}%>
		</div>
	<%}%>
	
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container"> 
			<%for(String s: errors) {%>
			<p class="error-message"><%=s%></p>
			<%}%>
		</div>
	<%}%>
	<table>
		<thead>
			<tr>
				<td>Student Id</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Grade</td>
				<td>Emergence Contact Number</td>
				<td colspan="2">Actions</td>
			</tr>
		</thead>
		<tbody>
			<%for(Student s: students){ %>
				<tr>
					<td><%=s.getId()%></td>
					<td><%=s.getFirstName()%></td>
					<td><%=s.getLastName()%></td>
					<td><%=s.getGrade()%></td>
					<td><%=s.getEmergencyContactNumber()%></td>
					<td><a href="${pageContext.servletContext.contextPath}/students?subAction=UPDATE&student_id=<%=s.getId()%>">update</a></td>
					<td><a href="${pageContext.servletContext.contextPath}/students?subAction=DELETE&student_id=<%=s.getId()%>">delete</a></td>
				</tr>
			<%} %>
		</tbody>
	</table>
</body>
</html>