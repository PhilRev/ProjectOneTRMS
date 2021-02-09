package dev.crane.entities;

public class RequestOut {
	private int request_id;
	private int course_id;
	private String course;
	private String sdate;
	private String edate;
	private String loc;
	private String app;
	private String comp;
	private int type;
	private String name;
	private String urgent;
	private int cost;
	private int procost;
	private String note;
	private String ovPay = "n";
	private String ovYtd = "n";
	private int ytd;
	private int left;
		
	public RequestOut() {
		super();
	}
	
	public RequestOut(int course_id, String course, String sdate, String edate, String loc, String app, String comp) {
		super();
		this.course_id = course_id;
		this.course = course;
		this.sdate = sdate;
		this.edate = edate;
		this.loc = loc;
		this.app = app;
		this.comp = comp;
	}

	public RequestOut(int request_id, int course_id, String course, String sdate, String edate, String loc, String app,
			String comp, int type, String name, String urgent, int cost, int procost, String note, String ovPay,
			String ovYtd, int ytd, int left) {
		super();
		this.request_id = request_id;
		this.course_id = course_id;
		this.course = course;
		this.sdate = sdate;
		this.edate = edate;
		this.loc = loc;
		this.app = app;
		this.comp = comp;
		this.type = type;
		this.name = name;
		this.urgent = urgent;
		this.cost = cost;
		this.procost = procost;
		this.note = note;
		this.ovPay = ovPay;
		this.ovYtd = ovYtd;
		this.ytd = ytd;
		this.left = left;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getProcost() {
		return procost;
	}

	public void setProcost(int procost) {
		this.procost = procost;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOvPay() {
		return ovPay;
	}

	public void setOvPay(String ovPay) {
		this.ovPay = ovPay;
	}

	public String getOvYtd() {
		return ovYtd;
	}

	public void setOvYtd(String ovYtd) {
		this.ovYtd = ovYtd;
	}

	public int getYtd() {
		return ytd;
	}

	public void setYtd(int ytd) {
		this.ytd = ytd;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "RequestOut [request_id=" + request_id + ", course_id=" + course_id + ", course=" + course + ", sdate="
				+ sdate + ", edate=" + edate + ", loc=" + loc + ", app=" + app + ", comp=" + comp + ", type=" + type
				+ ", name=" + name + ", urgent=" + urgent + ", cost=" + cost + ", procost=" + procost + ", note=" + note
				+ ", ovPay=" + ovPay + ", ovYtd=" + ovYtd + ", ytd=" + ytd + ", left=" + left + "]";
	}	
	
}
