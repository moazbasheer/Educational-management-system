import java.util.ArrayList;


public class Doctor extends User {

	private ArrayList<Course> course;
 
	Doctor(String name, String password) {
		super(name,password);
		course = new ArrayList<>();
	}

	public ArrayList<Course> getCourse() {
		return course;
	}
}
