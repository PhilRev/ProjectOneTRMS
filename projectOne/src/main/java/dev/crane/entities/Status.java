package dev.crane.entities;

public class Status {

	private int status_id;
	private String status;
	private String date_emp_request;
	private String add_info_req;
	private String date_sup_approve;
	private String date_dep_head_approve;
	private String date_benco_approve;
	private String grade;
	private String payout_override = "n";
	private String year_limit_override = "n";
	
	public Status() {
		super();
	}

	public Status(String status, String date_emp_request, String add_info_req, String date_sup_approve,
			String date_dep_head_approve, String date_benco_approve, String grade, String payout_override,
			String year_limit_override) {
		super();
		this.status = status;
		this.date_emp_request = date_emp_request;
		this.add_info_req = add_info_req;
		this.date_sup_approve = date_sup_approve;
		this.date_dep_head_approve = date_dep_head_approve;
		this.date_benco_approve = date_benco_approve;
		this.grade = grade;
		this.payout_override = payout_override;
		this.year_limit_override = year_limit_override;
	}

	public Status(int status_id, String status, String date_emp_request, String add_info_req, String date_sup_approve,
			String date_dep_head_approve, String date_benco_approve, String grade, String payout_override,
			String year_limit_override) {
		super();
		this.status_id = status_id;
		this.status = status;
		this.date_emp_request = date_emp_request;
		this.add_info_req = add_info_req;
		this.date_sup_approve = date_sup_approve;
		this.date_dep_head_approve = date_dep_head_approve;
		this.date_benco_approve = date_benco_approve;
		this.grade = grade;
		this.payout_override = payout_override;
		this.year_limit_override = year_limit_override;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate_emp_request() {
		return date_emp_request;
	}

	public void setDate_emp_request(String date_emp_request) {
		this.date_emp_request = date_emp_request;
	}

	public String getAdd_info_req() {
		return add_info_req;
	}

	public void setAdd_info_req(String add_info_req) {
		this.add_info_req = add_info_req;
	}

	public String getDate_sup_approve() {
		return date_sup_approve;
	}

	public void setDate_sup_approve(String date_sup_approve) {
		this.date_sup_approve = date_sup_approve;
	}

	public String getDate_dep_head_approve() {
		return date_dep_head_approve;
	}

	public void setDate_dep_head_approve(String date_dep_head_approve) {
		this.date_dep_head_approve = date_dep_head_approve;
	}

	public String getDate_benco_approve() {
		return date_benco_approve;
	}

	public void setDate_benco_approve(String date_benco_approve) {
		this.date_benco_approve = date_benco_approve;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPayout_override() {
		return payout_override;
	}

	public void setPayout_override(String payout_override) {
		this.payout_override = payout_override;
	}

	public String getYear_limit_override() {
		return year_limit_override;
	}

	public void setYear_limit_override(String year_limit_override) {
		this.year_limit_override = year_limit_override;
	}

	@Override
	public String toString() {
		return "Status [status_id=" + status_id + ", status=" + status + ", date_emp_request=" + date_emp_request
				+ ", add_info_req=" + add_info_req + ", date_sup_approve=" + date_sup_approve
				+ ", date_dep_head_approve=" + date_dep_head_approve + ", date_benco_approve=" + date_benco_approve
				+ ", grade=" + grade + ", payout_override=" + payout_override + ", year_limit_override="
				+ year_limit_override + "]";
	}
	
}
