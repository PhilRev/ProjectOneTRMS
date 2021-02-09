package dev.crane.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.crane.entities.Status;
import dev.crane.util.JDBCConnection;

public class StatusRepositoryImpl implements StatusRepository {

	private static Connection conn = JDBCConnection.getConnected();
	private static String sql = "";
	
	public boolean newStatus(Status status) {
		try {
			sql = "CALL add_status(?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, status.getStatus());
			cs.setString(2, status.getDate_emp_request());
			cs.setString(3, status.getAdd_info_req());
			cs.setString(4, status.getDate_sup_approve());
			cs.setString(5, status.getDate_dep_head_approve());
			cs.setString(6, status.getDate_benco_approve());
			cs.setString(7, status.getGrade());
			cs.setString(8, status.getPayout_override());
			cs.setString(9, status.getYear_limit_override());
			
			cs.execute();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public List<Status> getAllStatuses() {
		List<Status> statuses = new ArrayList<Status>();
		try {
			sql = "SELECT * FROM status";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Status status = new Status();
				status.setStatus_id(rs.getInt("STATUS_ID"));
				status.setStatus(rs.getString("STATUS"));
				status.setDate_emp_request(rs.getString("DATE_EMP_REQUEST"));
				status.setAdd_info_req(rs.getString("ADD_INFO_REQ"));
				status.setDate_sup_approve(rs.getString("DATE_SUP_APPROVE"));
				status.setDate_dep_head_approve(rs.getString("DATE_DEP_HEAD_APPROVE"));
				status.setDate_benco_approve(rs.getString("DATE_BENCO_APPROVE"));
				status.setGrade(rs.getString("GRADE"));
				status.setPayout_override(rs.getString("PAYOUT_OVERRIDE"));
				status.setYear_limit_override(rs.getString("YEAR_LIMIT_OVERRIDE"));
				
				statuses.add(status);			
			
			}
			return statuses;
			
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public Status getStatusById(int status_id) {
		try {
			sql = "SELECT * FROM status WHERE status_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status_id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Status status = new Status();
				status.setStatus_id(rs.getInt("STATUS_ID"));
				status.setStatus(rs.getString("STATUS"));
				status.setDate_emp_request(rs.getString("DATE_EMP_REQUEST"));
				status.setAdd_info_req(rs.getString("ADD_INFO_REQ"));
				status.setDate_sup_approve(rs.getString("DATE_SUP_APPROVE"));
				status.setDate_dep_head_approve(rs.getString("DATE_DEP_HEAD_APPROVE"));
				status.setDate_benco_approve(rs.getString("DATE_BENCO_APPROVE"));
				status.setGrade(rs.getString("GRADE"));
				status.setPayout_override(rs.getString("PAYOUT_OVERRIDE"));
				status.setYear_limit_override(rs.getString("YEAR_LIMIT_OVERRIDE"));
				
				return status;			
			
			}
			return null;
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public boolean updateStatus(Status status) {
		try {
			sql = "UPDATE status SET status = ?, date_emp_request = ?, add_info_req = ?, date_sup_approve = ?, date_dep_head_approve = ?, date_benco_approve = ?, grade = ?, payout_override = ?, year_limit_override = ? WHERE status_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status.getStatus());
			ps.setString(2, status.getDate_emp_request());
			ps.setString(3, status.getAdd_info_req());
			ps.setString(4, status.getDate_sup_approve());
			ps.setString(5, status.getDate_dep_head_approve());
			ps.setString(6, status.getDate_benco_approve());
			ps.setString(7, status.getGrade());
			ps.setString(8, status.getPayout_override());
			ps.setString(9, status.getYear_limit_override());
			ps.setString(10, Integer.toString(status.getStatus_id()));
			
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public boolean deleteStatusById(int status_id) {
		try {
			sql = "DELETE status WHERE status_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status_id);
			
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

}
