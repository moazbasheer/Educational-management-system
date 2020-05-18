package edu;
import java.util.ArrayList;


public class Doctor {
	public String name;
	public String password;
	public ArrayList<Course> course = new ArrayList<>();

	Doctor() {
		course = new ArrayList<>();
	}
 
	Doctor(String Name, String Password) {
		name = Name;
		password = Password;
		course = new ArrayList<>();
	}
}
