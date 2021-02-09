package dev.crane.entities;

import java.sql.Blob;

public class Request {

	private int request_id;
	private int employee_id;
	private int training_id;
	private int benco_id;
	private int status_id;
	private int requested_reimbursement;
	private String requested_pass_criteria;
	private String requested_start_date;
	private String requested_end_date;
	private String justification;
	
	private Blob supporting_doc;
	
	public Request() {
		super();
	}
	
	public Request(int request_id, int employee_id, int training_id, int benco_id, int status_id) {
		super();
		this.request_id = request_id;
		this.employee_id = employee_id;
		this.training_id = training_id;
		this.benco_id = benco_id;
		this.status_id = status_id;
		this.requested_reimbursement = 0;
		this.requested_start_date = null;
		this.requested_end_date = null;
		this.justification = null;
		this.supporting_doc = null;
		
	}

	public Request(int employee_id, int training_id, int benco_id, int status_id, int requested_reimbursement,
			String requested_pass_criteria, String requested_start_date, String requested_end_date,
			String justification, Blob supporting_doc) {
		super();
		this.employee_id = employee_id;
		this.training_id = training_id;
		this.benco_id = benco_id;
		this.status_id = status_id;
		this.requested_reimbursement = requested_reimbursement;
		this.requested_pass_criteria = requested_pass_criteria;
		this.requested_start_date = requested_start_date;
		this.requested_end_date = requested_end_date;
		this.justification = justification;
		this.supporting_doc = supporting_doc;
	}

	public Request(int request_id, int employee_id, int training_id, int benco_id, int status_id,
			int requested_reimbursement, String requested_pass_criteria, String requested_start_date,
			String requested_end_date, String justification, Blob supporting_doc) {
		super();
		this.request_id = request_id;
		this.employee_id = employee_id;
		this.training_id = training_id;
		this.benco_id = benco_id;
		this.status_id = status_id;
		this.requested_reimbursement = requested_reimbursement;
		this.requested_pass_criteria = requested_pass_criteria;
		this.requested_start_date = requested_start_date;
		this.requested_end_date = requested_end_date;
		this.justification = justification;
		this.supporting_doc = supporting_doc;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getTraining_id() {
		return training_id;
	}

	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}

	public int getBenco_id() {
		return benco_id;
	}

	public void setBenco_id(int benco_id) {
		this.benco_id = benco_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getRequested_reimbursement() {
		return requested_reimbursement;
	}

	public void setRequested_reimbursement(int requested_reimbursement) {
		this.requested_reimbursement = requested_reimbursement;
	}

	public String getRequested_pass_criteria() {
		return requested_pass_criteria;
	}

	public void setRequested_pass_criteria(String requested_pass_criteria) {
		this.requested_pass_criteria = requested_pass_criteria;
	}

	public String getRequested_start_date() {
		return requested_start_date;
	}

	public void setRequested_start_date(String requested_start_date) {
		this.requested_start_date = requested_start_date;
	}

	public String getRequested_end_date() {
		return requested_end_date;
	}

	public void setRequested_end_date(String requested_end_date) {
		this.requested_end_date = requested_end_date;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Blob getSupporting_doc() {
		return supporting_doc;
	}

	public void setSupporting_doc(Blob supporting_doc) {
		this.supporting_doc = supporting_doc;
	}

	@Override
	public String toString() {
		return "Request [request_id=" + request_id + ", employee_id=" + employee_id + ", training_id=" + training_id
				+ ", benco_id=" + benco_id + ", status_id=" + status_id + ", requested_reimbursement="
				+ requested_reimbursement + ", requested_pass_criteria=" + requested_pass_criteria
				+ ", requested_start_date=" + requested_start_date + ", requested_end_date=" + requested_end_date
				+ ", justification=" + justification + ", supporting_doc=" + supporting_doc + "]";
	}

	
}
