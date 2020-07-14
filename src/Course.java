import java.util.ArrayList;

public class Course {
	private String name;
	private long ID;
	private String docName;
	private ArrayList<Student> stud;
	private ArrayList<Assignment> assignment;

	Course(String name, long ID, String doc) {
		this.name = name;
		this.ID = ID;
		this.docName = doc;
		this.assignment = new ArrayList<>();
		this.stud = new ArrayList<>();
	}

	public ArrayList<Assignment> getAssignment() {
		return assignment;
	}

	public ArrayList<Student> getStud() {
		return stud;
	}

	public long getID() {
		return ID;
	}

	public String getDocName() {
		return docName;
	}

	public String getName() {
		return name;
	}
}
 
