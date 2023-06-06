<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.students.model.Student" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Update Student</title>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/listStudent/students.css"/>
	<jsp:include page="../common/nav-bar.jspf"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/common/nav-bar.css" />
	<%Student student = (Student)request.getAttribute("student"); %>
	
	<%if(student == null){ %>
		<p>Unable to fetch Student Data</p>
	<%}else{%>
		<div class="form-container" id="form-container">
		<form action="students" method="post" id="student-form">
			<input name="subAction" value="MODIFY_SUBMIT" hidden="true"/>
			<div class="form-control-group">
				<label>Student id</label>
				<input 
					type="number" 
					id="student_id" 
					name="Student_id" 
					oninvalid="setCustomValidity('Enter Student Id')"
					oninput="this.setCustomValidity('')"
					value="<%=student.getId()%>"
					readonly
					required>
			</div>
			<div class="form-control-group">
				<label>First Name</label>
				<input type="text" 
					id="firstName" 
					name="firstName" 
					oninvalid="setCustomValidity('Enter First Name')"
					oninput="this.setCustomValidity('')"
					value="<%=student.getFirstName()%>"
					required>
			</div>
			<div class="form-control-group">
				<label>Last Name</label>
				<input type="text" 
						id="lastName" 
						name="lastName"
						oninvalid="setCustomValidity('Enter Last Name')"
						oninput="this.setCustomValidity('')"
						value="<%=student.getLastName()%>"
						required>
			</div>
			<div class="form-control-group">
				<label>Grade</label>
				<input type="text" 
					   id="grade" 
					   name="grade" 
					   oninvalid="setCustomValidity('Enter Grade')"
					   oninput="this.setCustomValidity('')"
					   value="<%=student.getGrade()%>"
					   required>
			</div>
			<div class="form-control-group">
				<label>Emergency Contact Number</label>
				<input type="tel" 
						id="emno" 
						name="Emergeyno" 
						oninvalid="setCustomValidity('Enter 10 digit Emergency Contact Number')"
						oninput="this.setCustomValidity('')"
						value="<%=student.getEmergencyContactNumber() %>"
						pattern="[789][0-9]{9}" required>
			</div>
			<div class="form-control-group">
				<input type="submit" value="Update Student" class="btn-submit">
			</div>
		</form>
	</div>
	<% }%>
</body>
</html>