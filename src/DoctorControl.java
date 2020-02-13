import java.util.Scanner;

public class DoctorControl {
	public Scanner scan = new Scanner(System.in);
	public int currentDoctornum = -1;
	public int currentCoursenum = -1;
	public int signup = 0, back = 0, logout = 0;

	public void start() {
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
				int x = getDoctorMenuChoice();
				executeDoctorMenuChoice(x);
				if (logout == 1)
					break;
			}
		}
	}

	public int getDoctorMenuChoice() {
		int choice;
		System.out.print("Please make a choice:\n");
		System.out
				.print("1-Create course\n2-View a course\n3-List Courses\n4-Log out\n");

		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < 1 || choice > 4);

		return choice;
	}

	public void executeDoctorMenuChoice(int choice) {
		if (choice == 1)
			CreateCourse();
		else if (choice == 2)
			ViewCourse();
		else if (choice == 3)
			ListCourses();
		else
			logout = 1;
	}

	public void ListCourses() {
		int x = Database.doctors.get(currentDoctornum).course.size();
		System.out.printf("You have %d courses :", x);

		for (int i = 0; i < x; i++) {
			System.out
					.println((i + 1)
							+ "-"
							+ Database.doctors.get(currentDoctornum).course
									.get(i).name);
		}
	}

	public void CreateCourse() {
		System.out.print("Enter course name : ");
		String nam = scan.nextLine();
		nam = scan.nextLine();

		System.out.print("Enter course ID number : ");
		long id = scan.nextLong();

		Course r = new Course(nam, id,
				Database.doctors.get(currentDoctornum).name);
		Database.courses.add(new Course(nam, id, Database.doctors
				.get(currentDoctornum).name));
		Database.doctors.get(currentDoctornum).course.add(r);
		System.out.print("Course created successfully\n");
	}

	public void ViewCourse() {
		ListCourses();
		System.out.print("Please make a choice : \n");
		int x = Database.doctors.get(currentDoctornum).course.size();
		int choice = getChoice(1, x);
		currentCoursenum = choice - 1;

		System.out.print("Course name: "
				+ Database.courses.get(currentCoursenum).name + "\n");
		System.out.print("Course ID: "
				+ Database.courses.get(currentCoursenum).ID + "\n");
		System.out.print("Doctor name: "
				+ Database.courses.get(currentCoursenum).docName + "\n");

		int sz = Database.courses.get(currentCoursenum).assignment.size();
		System.out.print("Course has " + sz + " assignments\n");
		for (int i = 0; i < sz; i++) {
			printAssignment(Database.courses.get(currentCoursenum).assignment.get(i),
					i + 1);
		}
		System.out
				.print("1-Create Assignment\n2-View Student\n3-Show grades report\n4-Back\n");
		System.out.println("Make a choice please :");
		int choice2 = getChoice(1, 4);

		if (choice2 == 1) {
			CreateAssignment();
		} else if (choice2 == 2) {
			while (true) {
				int back2 = ViewStudent();
				if (back2 == -1)
					break;
			}
		} else if (choice2 == 3) {
			ShowGradesReport();
		}

	}

	public void ShowGradesReport() {
		for (int i = 0; i < Database.courses.get(currentCoursenum).stud.size(); i++) {
			long totalgrades = 0, ttotalgrades = 0, numRegistered = 0;

			for (int j = 0; j < Database.courses.get(currentCoursenum).stud
					.get(i).assignment.size(); j++) {

				totalgrades += Database.courses.get(currentCoursenum).stud
						.get(i).assignment.get(j).grade;
				ttotalgrades += Database.courses.get(currentCoursenum).stud
						.get(i).assignment.get(j).fullgrade;

				if (Database.courses.get(currentCoursenum).stud.get(i).assignment
						.get(j).submitted == true) {
					numRegistered++;
				}

			}
			System.out.println("Student's name is: "
					+ Database.courses.get(currentCoursenum).stud.get(i).name);
			System.out.println("number of submitted is: " + numRegistered);
			System.out.println("Total grades: " + totalgrades + "/"
					+ ttotalgrades);

		}
	}

	public void CreateAssignment() {
		System.out.print("Write the question:\n");
		String q = scan.nextLine();

		System.out.print("Enter the grade of the assignment:\n");
		int a = scan.nextInt();
		String s = scan.nextLine();

		Database.courses.get(currentCoursenum).assignment.add(new Assignment(q,
				a));
		int x = Database.courses.get(currentCoursenum).stud.size();
		for (int i = 0; i < x; i++) {
			Database.courses.get(currentCoursenum).stud.get(i).assignment
					.add(new Assignment(q, a));
		}
	}

	public int ViewStudent() {
		int sz = Database.courses.get(currentCoursenum).assignment.size();
		System.out.print("Course has " + sz + " assignments\n");
		for (int i = 0; i < sz; i++) {
			printAssignment(
					Database.courses.get(currentCoursenum).assignment.get(i),
					i + 1);
		}
		System.out
				.print("If you want to get back enter -1 otherwise any number:\n");
		int ch = scan.nextInt();
		if (ch == -1)
			return -1;

		int x = Database.courses.get(currentCoursenum).stud.size();
		System.out.print("Course has " + x + " students\n");
		System.out.println("Please choose one of them from 1 to " + x);
		int choice = getChoice(1, x);
		Student st = Database.courses.get(currentCoursenum).stud
				.get(choice - 1);
		System.out.println("Student's name is " + st.name);
		for (int i = 0; i < st.assignment.size(); i++) {
			printStudentAssignment(st.assignment.get(i), i + 1);
		}

		System.out.print("Choose one assignment of the above:\n");
		int r = getChoice(1, st.assignment.size());
		System.out.println("Please choose from this list");
		System.out.println("1-Set or edit Grade\n2-Set a comment\n3-back\n");
		int choice2 = getChoice(1, 3);
		if (choice2 == 1) {
			System.out.print("Please enter the grade\n");
			System.out.print("the full grade is ");
			System.out.println(Database.courses.get(currentCoursenum).stud
					.get(choice - 1).assignment.get(r - 1).fullgrade);
			int gr = scan.nextInt();
			Database.courses.get(currentCoursenum).stud.get(choice - 1).assignment
					.get(r - 1).grade = gr;
		} else if (choice2 == 2) {
			System.out.print("Please enter a comment:\n");
			String s = scan.nextLine();
			Database.courses.get(currentCoursenum).stud.get(choice - 1).assignment
					.get(r).comment = s;
		}
		return 0;
	}

	public void printStudentAssignment(Assignment x, int num) {
		System.out.print("Assignment " + num + " ");
		if (x.submitted == true) {
			System.out.print("-submitted-");
		} else {
			System.out.print("-not sumbitted-");
		}
		if (x.submitted != false) {
			System.out.print(x.grade);
		} else {
			System.out.print("NA");
		}
		System.out.println("/" + x.fullgrade);
		if (x.submitted == true) {
			System.out.println("Answer is " + x.answer);
		}
	}

	public void printAssignment(Assignment x, int num) {
		System.out.print("Assignment " + num + " is ");
		System.out.println(x.question);
	}

	public int getChoice(int l, int r) {
		int choice;
		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < l || choice > r);
		return choice;
	}

	public void executeSignInUp(int choice) {
		if (choice == 1) {
			currentDoctornum = SignIn();
		} else if (choice == 2) {
			SignUp();
			signup = 1;
		} else
			back = 1;
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
		Database.doctors.add(new Doctor(name, pass));
		System.out.print("Your account is registered successfully\n");
	}

	int SignIn() {
		System.out.print("Please Enter your username\n");
		String name = scan.nextLine();
		name = scan.nextLine();
		System.out.print("Please Enter your password\n");
		String pass = scan.nextLine();
		while (Database.getDoctor(name, pass) == -1) {
			System.out
					.print("The username or the password is wrong\nPlease try again\n");
			System.out.print("Please Enter your username\n");
			name = scan.nextLine();
			System.out.print("Please Enter your password\n");
			pass = scan.nextLine();
		}
		return Database.getDoctor(name, pass);
	}

}
