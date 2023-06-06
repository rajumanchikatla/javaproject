package com.students.contstants;

public class SQLConstants {

	public static final String ADD_QUERY = "insert into list_students (id, firstName, lastName, grade,emergencyContactNumber) values(?, ?, ?,?,?)";

	public static final String LIST_STUDENTS = "select id ,firstName,lastName,grade,emergencyContactNumber from list_students";
	
	public static final String GET_STUDENT = "select id ,firstName,lastName,grade,emergencyContactNumber from list_students where id=?";
	
	public static final String UPDATE_STUDENT = "update list_students set firstName=? ,lastName=? ,grade=? ,emergencyContactNumber=?  where id=?";
	
	public static final String DELETE_STUDENT = "delete from list_students where id=?";
	
	public static final String SEARCH_STUDENTS = "select id ,firstName,lastName,grade,emergencyContactNumber from list_students where id!='0' ";
	
}
