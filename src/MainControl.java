import java.util.Scanner;


public class MainControl {
	public Scanner scan = new Scanner(System.in);
	public Database database;
	public StudentControl studentcon;
	public DoctorControl doctorcon;

	public MainControl() {
		database = new Database();
		studentcon = new StudentControl();
		doctorcon = new DoctorControl();
	}

	public void run() {
		while (true) {
			int doc = getDocStChoice();
			if (doc == 1)
				studentcon.start();
			else
				doctorcon.start();
		}
	}

	public int getDocStChoice() {
		System.out.print("Are you ?? \n1-Student \n2-Doctor\n");
		int choice;

		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < 1 || choice > 2);

		return choice;
	}
}
