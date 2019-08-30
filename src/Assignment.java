
public class Assignment {
	public String question;
	public String answer;
	public boolean submitted;
	public int grade;
	public int fullgrade;
	public String comment;
	
	public Assignment(String q,int a){
		this.question=q;
		this.answer="";
		this.submitted=false;
		this.grade=0;
		this.fullgrade=a;
		this.comment="";
	}
	
}
