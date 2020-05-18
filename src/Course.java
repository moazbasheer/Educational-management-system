package edu;
import java.util.ArrayList;

public class Course {
	public String name;
	public long ID;
	public String docName;
	public ArrayList<Student> stud;
	public ArrayList<Assignment> assignment;

	Course(String name, long ID, String doc) {
		this.name = name;
		this.ID = ID;
		this.docName = doc;
		this.assignment = new ArrayList<>();
		this.stud = new ArrayList<>();
	}
}
 
