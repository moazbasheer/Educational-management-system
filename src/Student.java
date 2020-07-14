import java.util.ArrayList;

public class Student extends User {

	private ArrayList<Course> registered_course;
	private ArrayList<Assignment> assignment;

	Student(String name,String password){
		super(name,password);
		registered_course=new ArrayList<Course>();
		assignment=new ArrayList<>();
	}

	public ArrayList<Course> getRegistered_course() {
		return registered_course;
	}

	public ArrayList<Assignment> getAssignment() {
		return assignment;
	}
}
