import java.util.*;
public class Doctor {
	String name;
	String password;
	ArrayList<Course> course=new ArrayList<>();
	
	Doctor(){
		course=new ArrayList<>();
	}
	
	Doctor(String x,String y){
		name=x;
		password=y;
		course=new ArrayList<>();
	}
	
}
