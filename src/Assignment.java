package edu;
public class Assignment {
	public String question;
	public String answer;
	public boolean submitted;
	public int grade;
	public int fullgrade;
	public String comment;

	public Assignment(String Question, int fullGrade) {
		this.question = Question;
		this.answer = "";
		this.submitted = false;
		this.grade = 0;
		this.fullgrade = fullGrade;
		this.comment = "";
	}
}

