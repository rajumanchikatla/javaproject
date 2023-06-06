package com.students.model;

public class Student {
	
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String grade;
	
	private long emergencyContactNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public long getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(long emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", grade=" + grade
				+ ", emergencyContactNumber=" + emergencyContactNumber + "]";
	}
	
	public boolean isValidForSearch() {
		if(id == 0 && (firstName ==null || firstName.isEmpty()) 
				&& (lastName ==null || lastName.isEmpty())
				&& (grade ==null || grade.isEmpty()))
			return false;
		else return true;
	}
}

