package dev.crane.entities;

public class TRF {
	int reqId;
	String name;
	String email;
	String pos;
	String supName;
	String course;
	String courseType;
	String loc;
	String sdate;
	String edate;
	String cost;
	String passCrit;
	int reimb;
	String repassCrit;
	String resdate;
	String reedate;
	String just;
	String notes;
	String aprove;
	String grade;
	
	public TRF() {
		super();
	}

	public TRF(int reqId, String name, String email, String pos, String supName, String course, String courseType,
			String loc, String sdate, String edate, String cost, String passCrit, int reimb, String repassCrit,
			String resdate, String reedate, String just, String notes, String aprove, String grade) {
		super();
		this.reqId = reqId;
		this.name = name;
		this.email = email;
		this.pos = pos;
		this.supName = supName;
		this.course = course;
		this.courseType = courseType;
		this.loc = loc;
		this.sdate = sdate;
		this.edate = edate;
		this.cost = cost;
		this.passCrit = passCrit;
		this.reimb = reimb;
		this.repassCrit = repassCrit;
		this.resdate = resdate;
		this.reedate = reedate;
		this.just = just;
		this.notes = notes;
		this.aprove = aprove;
		this.grade = grade;
	}

	public TRF(int reqId, String name, String email, String pos, String supName, String course, String courseType,
			String loc, String sdate, String edate, String cost, String passCrit, int reimb, String repassCrit,
			String resdate, String reedate, String just) {
		super();
		this.reqId = reqId;
		this.name = name;
		this.email = email;
		this.pos = pos;
		this.supName = supName;
		this.course = course;
		this.courseType = courseType;
		this.loc = loc;
		this.sdate = sdate;
		this.edate = edate;
		this.cost = cost;
		this.passCrit = passCrit;
		this.reimb = reimb;
		this.repassCrit = repassCrit;
		this.resdate = resdate;
		this.reedate = reedate;
		this.just = just;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getPassCrit() {
		return passCrit;
	}

	public void setPassCrit(String passCrit) {
		this.passCrit = passCrit;
	}

	public int getReimb() {
		return reimb;
	}

	public void setReimb(int reimb) {
		this.reimb = reimb;
	}

	public String getRepassCrit() {
		return repassCrit;
	}

	public void setRepassCrit(String repassCrit) {
		this.repassCrit = repassCrit;
	}

	public String getResdate() {
		return resdate;
	}

	public void setResdate(String resdate) {
		this.resdate = resdate;
	}

	public String getReedate() {
		return reedate;
	}

	public void setReedate(String reedate) {
		this.reedate = reedate;
	}

	public String getJust() {
		return just;
	}

	public void setJust(String just) {
		this.just = just;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAprove() {
		return aprove;
	}

	public void setAprove(String aprove) {
		this.aprove = aprove;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "TRF [reqId=" + reqId + ", name=" + name + ", email=" + email + ", pos=" + pos + ", supName=" + supName
				+ ", course=" + course + ", courseType=" + courseType + ", loc=" + loc + ", sdate=" + sdate + ", edate="
				+ edate + ", cost=" + cost + ", passCrit=" + passCrit + ", reimb=" + reimb + ", repassCrit="
				+ repassCrit + ", resdate=" + resdate + ", reedate=" + reedate + ", just=" + just + ", notes=" + notes
				+ ", aprove=" + aprove + ", grade=" + grade + "]";
	}
	
}