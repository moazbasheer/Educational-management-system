package edu;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentControl {
	public Scanner scan = new Scanner(System.in);
	public int currentStudentnum = -1, currentCoursenum = -1;
	private int signup = 0;
	private int back = 0;
	private int logout = 0;

	void start() {
		while (true) {
			signup = 0;
			back = 0;
			logout = 0;

			int choice = ch_signInUp();
			executeSignInUp(choice);

			if (signup == 1)
				continue;
			else if (back == 1)
				break;

			while (true) {
				int x = getStudentMenuChoice();
				executeStudentMenuChoice(x);
				if (logout == 1)
					break;
			}
		}
	}

	public void executeStudentMenuChoice(int x) {
		if (x == 1)
			registerInCourse();
		else if (x == 2)
			ViewCourses();
		else if (x == 3)
			ListCourses();
		else {
			currentStudentnum = -1;
			currentCoursenum = -1;
			logout = 1;
		}
	}

	public void ViewCourses() {
		ListCourses();
		if (Database.students.get(currentStudentnum).registered_course.size() > 0) {
			System.out.print("Please make a choice\n");
			int choice = getChoice(1,
					Database.students.get(currentStudentnum).registered_course
							.size());
			currentCoursenum = choice - 1;
			System.out.print("Course name: "
					+ Database.courses.get(currentCoursenum).name + "\n");
			System.out.print("Course ID: "
					+ Database.courses.get(currentCoursenum).ID + "\n");
			System.out.print("Doctor name: "
					+ Database.courses.get(currentCoursenum).docName + "\n\n");
			int sz = Database.courses.get(currentCoursenum).assignment.size();
			System.out.print("Course has " + sz + " assignments\n");
			int stnum = -1;
			// get Student number in the course
			for (int i = 0; i < Database.courses.get(currentCoursenum).stud
					.size(); i++) {
				if (Database.courses.get(currentCoursenum).stud.get(i).name
						.equals(Database.students.get(currentStudentnum).name)) {
					stnum = i;
					break;
				}
			}

			for (int i = 0; i < sz; i++) {
				printAssignment(Database.courses.get(currentCoursenum).stud
						.get(stnum).assignment.get(i), i + 1);
			}

			System.out.println();

			System.out
					.print("1-Unregister course\n2-Submit Solution\n3-Back\n");
			System.out.println("Make a choice please :");
			choice = getChoice(1, 3);

			if (choice == 1) {
				UnregisterCourse();
			} else if (choice == 2) {
				SubmitSolution(stnum);
			}
		}
	}

	public void printAssignment(Assignment x, int num) {
		System.out.print("Assignment " + num + " ");
		if (x.submitted)
			System.out.print("-submitted-");
		else
			System.out.print("-not sumbitted-");
		if (x.grade != -1)
			System.out.print(x.grade);
		else
			System.out.print("NA");
		System.out.println("/" + x.fullgrade);
	}

	public void SubmitSolution(int stnum) {
		int sz = Database.courses.get(currentCoursenum).assignment.size();
		System.out.println("You have " + sz + " assignments\n");

		for (int i = 0; i < sz; i++) {
			printAssignment(
					Database.courses.get(currentCoursenum).assignment.get(i),
					i + 1);
		}

		if (sz > 0) {
			System.out.print("Choose one of them please:\n");
			int choice;
			do {
				choice = getChoice(1, sz);
			} while (Database.courses.get(currentCoursenum).stud.get(stnum).assignment
					.get(choice - 1).submitted);
			System.out.print("The question is :\n");
			System.out.println(Database.courses.get(currentCoursenum).stud
					.get(stnum).assignment.get(choice - 1).question);
			System.out.print("Write your answer\n");
			String s = scan.nextLine();
			Database.courses.get(currentCoursenum).stud.get(stnum).assignment
					.get(choice - 1).answer = scan.nextLine();
			Database.courses.get(currentCoursenum).stud.get(stnum).assignment
					.get(choice - 1).submitted = true;
			System.out.print("Assignment is submitted successfully\n");
		}
	}

	public void UnregisterCourse() {
		int sz = Database.courses.get(currentCoursenum).stud.size();
		for (int i = 0; i < sz; i++) {
			if (Database.courses.get(currentCoursenum).stud.get(i).equals(
					Database.students.get(currentStudentnum))) {
				Database.courses.get(currentCoursenum).stud.remove(i);
				break;
			}
		}
		sz = Database.students.get(currentStudentnum).registered_course.size();
		for (int i = 0; i < sz; i++) {
			if (Database.students.get(currentStudentnum).registered_course.get(
					i).equals(Database.courses.get(currentCoursenum))) {
				Database.students.get(currentStudentnum).registered_course
						.remove(i);
				break;
			}
		}
		System.out.print("You unregistered the course successfully\n\n");
	}

	public int getChoice(int l, int r) {
		int choice;
		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < l || choice > r);
		return choice;
	}

	public void ListCourses() {
		int sz = Database.students.get(currentStudentnum).registered_course
				.size();
		System.out.println("Your have " + sz + " courses :\n");

		for (int i = 0; i < sz; i++) {
			System.out
					.println((i + 1)
							+ "-"
							+ Database.students.get(currentStudentnum).registered_course
									.get(i).name);
		}

		System.out.println();
	}

	public boolean isRegistered(Course cr) {
		for (int i = 0; i < Database.students.get(currentStudentnum).registered_course
				.size(); i++) {
			if (Database.students.get(currentStudentnum).registered_course.get(
					i).equals(cr)) {
				return true;
			}
		}
		return false;
	}

	public void registerInCourse() {
		System.out.print("Please choose a course:\n");
		int sz = Database.courses.size();
		int f = 1;
		ArrayList<Course> reg = new ArrayList<>();

		for (int i = 0; i < sz; i++) {
			if (!isRegistered(Database.courses.get(i))) {
				System.out.println((f++) + "-" + Database.courses.get(i).name);
				reg.add(Database.courses.get(i));
			}
		}

		if (f - 1 > 0) {
			System.out.print("Please choose a course:\n");
			int choice;
			do {
				System.out.print("Please Enter valid choice:\n");
				choice = scan.nextInt();
			} while (choice < 1 || choice > f - 1);

			Database.students.get(currentStudentnum).registered_course.add(reg
					.get(choice - 1));
			int x = -1;
			for (int i = 0; i < Database.courses.size(); i++) {
				if (Database.courses.get(i).equals(reg.get(choice - 1))) {
					x = i;
				}
			}
			Database.courses.get(x).stud.add(Database.students
					.get(currentStudentnum));
			int n = Database.courses.get(x).stud.size() - 1;
			// Add assignments to student
			for (int i = 0; i < Database.courses.get(x).assignment.size(); i++) {
				Database.courses.get(x).stud.get(n).assignment
						.add(Database.courses.get(choice - 1).assignment.get(i));
			}
			System.out.print("Your registeration completed\n");
		} else
			System.out.println("Sorry there is no unregistered courses\n");
	}

	public void executeSignInUp(int choice) {
		if (choice == 1) {
			currentStudentnum = SignIn(); // SignIn() gets the object of the
											// student that logged in
		} else if (choice == 2) {
			SignUp();
			signup = 1;
		} else
			back = 1;
	}

	public int getStudentMenuChoice() {
		int choice;
		System.out.print("Please make a choice:\n");
		System.out
				.print("1-Register in course\n2-View a course\n3-List Courses\n4-Log out\n");
		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < 1 || choice > 4);
		return choice;
	}

	public int ch_signInUp() {
		int choice;
		System.out
				.print("Please make a choice \n1-sign in\n2-sign up\n3-back\n");
		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < 1 || choice > 3);
		return choice;
	}

	void SignUp() {
		System.out.print("Please Enter your username\n");
		String name = scan.nextLine();
		name = scan.nextLine();

		System.out.print("Please Enter your password\n");
		String pass = scan.nextLine();

		while (!Database.isValidStudentUsername(name)) {
			System.out
					.println("There is another user has same name:\nTry again\n");
			System.out.print("Please Enter your username\n");
			name = scan.nextLine();

			System.out.print("Please Enter your password\n");
			pass = scan.nextLine();
		}

		Database.students.add(new Student(name, pass));
		System.out.print("Your account is registered successfully\n");
	}

	int SignIn() {
		System.out.print("Please Enter your username\n");
		String name = scan.nextLine();
		name = scan.nextLine();

		System.out.print("Please Enter your password\n");
		String pass = scan.nextLine();

		while (Database.getStudent(name, pass) == -1) {
			System.out
					.print("The username or the password is wrong\nPlease try again\n");
			System.out.print("Please Enter your username\n");
			name = scan.nextLine();

			System.out.print("Please Enter your password\n");
			pass = scan.nextLine();

		}
		System.out.println("Logged in successfully\n");
		return Database.getStudent(name, pass);
	}
}
