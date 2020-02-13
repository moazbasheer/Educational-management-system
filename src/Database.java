import java.util.ArrayList;

public class Database {

	public static ArrayList<Doctor> doctors = new ArrayList<>();
	public static ArrayList<Course> courses = new ArrayList<>();
	public static ArrayList<Student> students = new ArrayList<>();

	Database() {
		courses.add(new Course("CS", 12, "Ramly"));
		doctors.add(new Doctor("Ramly", "123"));
		students.add(new Student("moaz", "123"));
		courses.get(0).stud.add(students.get(0));
		students.get(0).registered_course.add(courses.get(0));
		courses.get(0).stud.get(0).assignment.add(new Assignment(
				"Define Algorithm", 40));
		courses.get(0).assignment.add(new Assignment("Define Algorithm", 40));
		doctors.get(0).course.add(courses.get(0));
	}

	static int getStudent(String username, String password) {
		ArrayList<Student> x = Database.students;
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).name.equals(username)
					&& x.get(i).password.equals(password))
				return i;
		}
		return -1;
	}

	static int getDoctor(String username, String password) {
		ArrayList<Doctor> x = Database.doctors;
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).name.equals(username)
					&& x.get(i).password.equals(password))
				return i;
		}
		return -1;
	}
 
	static boolean isValidStudentUsername(String n) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).name.equals(n)) {
				return false;
			}
		}
		return true;
	}

	static boolean isValidDoctorUsername(String n) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).name.equals(n)) {
				return false;
			}
		}
		return true;
	}

}
