package dev.crane.entities;

public class Jobs {

	private int job_id;
	private String job_des;
	private int dep_head_id;
	
	public Jobs() {
		super();
	}

	public Jobs(String job_des, int dep_head_id) {
		super();
		this.job_des = job_des;
		this.dep_head_id = dep_head_id;
	}

	public Jobs(int job_id, String job_des, int dep_head_id) {
		super();
		this.job_id = job_id;
		this.job_des = job_des;
		this.dep_head_id = dep_head_id;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public String getJob_des() {
		return job_des;
	}

	public void setJob_des(String job_des) {
		this.job_des = job_des;
	}

	public int getDep_head_id() {
		return dep_head_id;
	}

	public void setDep_head_id(int dep_head_id) {
		this.dep_head_id = dep_head_id;
	}

	@Override
	public String toString() {
		return "Jobs [job_id=" + job_id + ", job_des=" + job_des + ", dep_head_id=" + dep_head_id + "]";
	}	
	
}
