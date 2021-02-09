package dev.crane.entities;

public class Employee {
	
	private int employee_id;
	private String fname;
	private String lname;
	private String username;
	private String userpass;
	private String email;
	private int job_id;
	private int supervisor_id;

	public Employee() {
		super();
	}

	public Employee(String fname, String lname, String username, String userpass, String email, int job_id,
			int supervisor_id) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.userpass = userpass;
		this.email = email;
		this.job_id = job_id;
		this.supervisor_id = supervisor_id;
	}

	public Employee(int employee_id, String fname, String lname, String username, String userpass, String email,
			int job_id, int supervisor_id) {
		super();
		this.employee_id = employee_id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.userpass = userpass;
		this.email = email;
		this.job_id = job_id;
		this.supervisor_id = supervisor_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getSupervisor_id() {
		return supervisor_id;
	}

	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", fname=" + fname + ", lname=" + lname + ", username="
				+ username + ", userpass=" + userpass + ", email=" + email + ", job_id=" + job_id + ", supervisor_id="
				+ supervisor_id + "]";
	}
	
}
