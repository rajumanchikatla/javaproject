<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Student</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/nav-bar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/listStudent/students.css"/>
</head>
<body>
<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
	<jsp:include page="../common/nav-bar.jspf"></jsp:include>
		
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container"> 
			<%for(String s: errors) {%>
			<p class="error-message"><%=s%></p>
			<%}%>
		</div>
	<%}%>
	
	<div class="form-container" id="form-container">
		<form action="students" method="post" id="student-form">
			<input name="subAction" value="SEARCH_SUBMIT" hidden="true"/>
			<div class="form-control-group">
				<label>Student id</label>
				<input 
					type="number" 
					id="student_id" 
					name="Student_id" 
					>
			</div>
			<div class="form-control-group">
				<label>First Name</label>
				<input type="text" 
					id="firstName" 
					name="firstName" 
					>
			</div>
			<div class="form-control-group">
				<label>Last Name</label>
				<input type="text" 
						id="lastName" 
						name="lastName"
						>
			</div>
			<div class="form-control-group">
				<label>Grade</label>
				<input type="text" 
					   id="grade" 
					   name="grade" 
					   >
			</div>
			<div class="form-control-group">
				<input type="submit" value="Search Student" class="btn-submit">
			</div>
		</form>
	</div>
</body>
</html>