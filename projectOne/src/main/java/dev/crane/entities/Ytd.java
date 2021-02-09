package dev.crane.entities;

public class Ytd {

	private int employee_id;
	private int payout;
	
	public Ytd() {
		super();
	}

	public Ytd(int employee_id, int payout) {
		super();
		this.employee_id = employee_id;
		this.payout = payout;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getPayout() {
		return payout;
	}

	public void setPayout(int payout) {
		this.payout = payout;
	}

	@Override
	public String toString() {
		return "Ytd [employee_id=" + employee_id + ", payout=" + payout + "]";
	}
		
}
