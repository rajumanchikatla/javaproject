<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
</head>
<body>

<link rel="stylesheet" href="${pageContext.request.contextPath}/listStudent/students.css"/>
	<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
	<jsp:include page="../common/nav-bar.jspf"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/common/nav-bar.css" />
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container"> 
			<%for(String s: errors) {%>
			<p class="error-message"><%=s%></p>
			<%}%>
		</div>
	<%}%>
	
	<div class="form-container" id="form-container">
		<form action="students" method="post" id="student-form">
			<input name="subAction" value="ADD_SUBMIT" hidden="true"/>
			<div class="form-control-group">
				<label>Student id</label>
				<input 
					type="number" 
					id="student_id" 
					name="Student_id" 
					oninvalid="setCustomValidity('Enter Student Id')"
					oninput="this.setCustomValidity('')"
					required>
			</div>
			<div class="form-control-group">
				<label>First Name</label>
				<input type="text" 
					id="firstName" 
					name="firstName" 
					oninvalid="setCustomValidity('Enter First Name')"
					oninput="this.setCustomValidity('')"
					required>
			</div>
			<div class="form-control-group">
				<label>Last Name</label>
				<input type="text" 
						id="lastName" 
						name="lastName"
						oninvalid="setCustomValidity('Enter Last Name')"
						oninput="this.setCustomValidity('')"
						required>
			</div>
			<div class="form-control-group">
				<label>Grade</label>
				<input type="text" 
					   id="grade" 
					   name="grade" 
					   oninvalid="setCustomValidity('Enter Grade')"
					   oninput="this.setCustomValidity('')"
					   required>
			</div>
			<div class="form-control-group">
				<label>Emergency Contact Number</label>
				<input type="tel" 
						id="emno" 
						name="Emergeyno" 
						oninvalid="setCustomValidity('Enter 10 digit Emergency Contact Number')"
						oninput="this.setCustomValidity('')"
						pattern="[789][0-9]{9}" required>
			</div>
			<div class="form-control-group">
				<input type="submit" value="Add Student" class="btn-submit">
			</div>
		</form>
	</div>
</body>
</html>