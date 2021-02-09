package dev.crane.entities;

public class Training {

	private int training_id;
	private int grading_id;
	private String course_name;
	private String course_type;
	private String course_cost;
	private String start_date;
	private String end_date;
	private String location;
	
	public Training() {
		super();
	}

	public Training(int grading_id, String course_name, String course_type, String course_cost, String start_date,
			String end_date, String location) {
		super();
		this.grading_id = grading_id;
		this.course_name = course_name;
		this.course_type = course_type;
		this.course_cost = course_cost;
		this.start_date = start_date;
		this.end_date = end_date;
		this.location = location;
	}

	public Training(int training_id, int grading_id, String course_name, String course_type, String course_cost,
			String start_date, String end_date, String location) {
		super();
		this.training_id = training_id;
		this.grading_id = grading_id;
		this.course_name = course_name;
		this.course_type = course_type;
		this.course_cost = course_cost;
		this.start_date = start_date;
		this.end_date = end_date;
		this.location = location;
	}

	public int getTraining_id() {
		return training_id;
	}

	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}

	public int getGrading_id() {
		return grading_id;
	}

	public void setGrading_id(int grading_id) {
		this.grading_id = grading_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}

	public String getCourse_cost() {
		return course_cost;
	}

	public void setCourse_cost(String course_cost) {
		this.course_cost = course_cost;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Training [training_id=" + training_id + ", grading_id=" + grading_id + ", course_name=" + course_name
				+ ", course_type=" + course_type + ", course_cost=" + course_cost + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", location=" + location + "]";
	}
	
}
