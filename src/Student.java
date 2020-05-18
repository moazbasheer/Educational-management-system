package edu;
import java.util.ArrayList;

public class Student {
	String name;
	String password;
	ArrayList<Course> registered_course;
	ArrayList<Assignment> assignment=new ArrayList<>();
	
	Student(){}
	
	Student(String Name,String Password){
		name=Name;
		password=Password;
		registered_course=new ArrayList<Course>();
	}
	
}
