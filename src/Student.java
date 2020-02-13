import java.util.*;

public class Student {
	String name;
	String password;
	ArrayList<Course> registered_course;
	ArrayList<Assignment> assignment=new ArrayList<>();
	
	Student(){}
	
	Student(String x,String y){
		name=x;
		password=y;
		registered_course=new ArrayList<Course>();
	}
	
}
