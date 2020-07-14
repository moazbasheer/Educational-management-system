import java.util.ArrayList;

public class Database {

	private static ArrayList<Doctor> doctors = new ArrayList<>();
	private static ArrayList<Course> courses = new ArrayList<>();
	private static ArrayList<Student> students = new ArrayList<>();

	Database() {
		courses.add(new Course("CS", 12, "Ramly"));
		doctors.add(new Doctor("Ramly", "123"));
		students.add(new Student("moaz", "123"));
		courses.get(0).getStud().add(students.get(0));
		students.get(0).getRegistered_course().add(courses.get(0));
		courses.get(0).getStud().get(0).getAssignment().add(new Assignment(
				"Define Algorithm", 40));
		courses.get(0).getAssignment().add(new Assignment("Define Algorithm", 40));
		doctors.get(0).getCourse().add(courses.get(0));
	}

	public static ArrayList<Student> getStudents() {
		return students;
	}

	public static ArrayList<Course> getCourses() {
		return courses;
	}

	public static ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	static int getStudent(String username, String password) {
		ArrayList<Student> x = Database.students;
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).getName().equals(username)
					&& x.get(i).getPassword().equals(password))
				return i;
		}
		return -1;
	}

	static int getDoctor(String username, String password) {
		ArrayList<Doctor> x = Database.doctors;
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).getName().equals(username)
					&& x.get(i).getPassword().equals(password))
				return i;
		}
		return -1;
	}
 
	static boolean isValidStudentUsername(String n) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getName().equals(n)) {
				return false;
			}
		}
		return true;
	}

	static boolean isValidDoctorUsername(String n) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getName().equals(n)) {
				return false;
			}
		}
		return true;
	}

}
