import java.util.Scanner;
public class MainControl {
	private Scanner scan = new Scanner(System.in);
	private Database database;
	private StudentControl studentcon;
	private DoctorControl doctorcon;

	public MainControl() {
		scan = new Scanner(System.in);
		database = new Database();
		studentcon = new StudentControl();
		doctorcon = new DoctorControl();
	}

	public void run() {
		while (true) {
			int doc = getUserState();
			if (doc == 1)
				studentcon.start();
			else
				doctorcon.start();
		}
	}

	public int getUserState() {
		System.out.print("Are you ?? \n1-Student \n2-Doctor\n");
		int choice;

		do {
			System.out.print("Please Enter valid choice:\n");
			choice = scan.nextInt();
		} while (choice < 1 || choice > 2);

		return choice;
	}
}
 
