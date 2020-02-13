import java.util.ArrayList;


public class Doctor {
	String name;
	String password;
	ArrayList<Course> course = new ArrayList<>();

	Doctor() {
		course = new ArrayList<>();
	}

	Doctor(String Name, String Password) {
		name = Name;
		password = Password;
		course = new ArrayList<>();
	}
}
