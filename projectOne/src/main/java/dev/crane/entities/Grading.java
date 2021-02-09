package dev.crane.entities;

public class Grading {

	private int grading_id;
	private String pass_criteria;
	
	public Grading() {
		super();
	}

	public Grading(String pass_criteria) {
		super();
		this.pass_criteria = pass_criteria;
	}

	public Grading(int grading_id, String pass_criteria) {
		super();
		this.grading_id = grading_id;
		this.pass_criteria = pass_criteria;
	}

	public int getGrading_id() {
		return grading_id;
	}

	public void setGrading_id(int grading_id) {
		this.grading_id = grading_id;
	}

	public String getPass_criteria() {
		return pass_criteria;
	}

	public void setPass_criteria(String pass_criteria) {
		this.pass_criteria = pass_criteria;
	}

	@Override
	public String toString() {
		return "grading [grading_id=" + grading_id + ", pass_criteria=" + pass_criteria + "]";
	}
	
}
