public class Assignment {
	private String question;
	private String answer;
	private String comment;
	private boolean isSubmitted;
	private int grade;
	private int fullGrade;
	boolean isSubmitted(){
		return isSubmitted;
	}
	boolean isGraded(){
		return grade != -1;
	}

	public int getGrade() {
		return grade;
	}

	public int getFullGrade() {
		return fullGrade;
	}
	public boolean submit(){
		isSubmitted = true;
		return true;
	}
	public Assignment(String Question, int fullGrade) {
		this.question = Question;
		this.answer = "";
		this.isSubmitted = false;
		this.grade = 0;
		this.fullGrade = fullGrade;
		this.comment = "";
	}

	public String getQuestion() {
		return question;
	}

	public boolean addAnAnswer(String answer) {
		this.answer = answer;
		return true;
	}

	public void putGrade(int gr) {
		grade = gr;
	}

	public void putComment(String s) {
		comment = s;
	}

	public String getAnswer() {
		return answer;
	}
}

