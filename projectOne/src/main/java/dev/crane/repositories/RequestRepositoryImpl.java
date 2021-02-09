package dev.crane.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.crane.entities.Request;
import dev.crane.util.JDBCConnection;

public class RequestRepositoryImpl implements RequestRepository {

	private static Connection conn = JDBCConnection.getConnected();
	private static String sql = "";

	public boolean newRequest(Request request) {
		try {
			sql = "CALL add_request(?,?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, Integer.toString(request.getEmployee_id()));
			cs.setString(2, Integer.toString(request.getTraining_id()));
			cs.setString(3, Integer.toString(request.getBenco_id()));
			cs.setString(4, Integer.toString(request.getStatus_id()));
			cs.setString(5, Integer.toString(request.getRequested_reimbursement()));
			cs.setString(6, request.getRequested_pass_criteria());
			cs.setString(7, request.getRequested_start_date());
			cs.setString(8, request.getRequested_end_date());
			cs.setString(9, request.getJustification());
			cs.setBlob(10, request.getSupporting_doc());

			cs.execute();
			return true;

		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public List<Request> getAllRequests() {
		List<Request> requests = new ArrayList<Request>();
		try {
			sql = "SELECT * FROM request";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Request request = new Request();
				request.setRequest_id(rs.getInt("REQUEST_ID"));
				request.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				request.setTraining_id(rs.getInt("TRAINING_ID"));
				request.setBenco_id(rs.getInt("BENCO_ID"));
				request.setStatus_id(rs.getInt("STATUS_ID"));
				request.setRequested_reimbursement(rs.getInt("REQUESTED_REIMBURSEMENT"));
				request.setRequested_pass_criteria(rs.getString("REQUESTED_PASS_CRITERIA"));
				request.setRequested_start_date(rs.getString("REQUESTED_START_DATE"));
				request.setRequested_end_date(rs.getString("REQUESTED_END_DATE"));
				request.setJustification(rs.getString("JUSTIFICATION"));
				request.setSupporting_doc(rs.getBlob("SUPPORTING_DOC"));
				requests.add(request);
			}
			return requests;
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public Request getRequestById(int request_id) {
		try {
			sql = "SELECT * FROM request WHERE request_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, request_id);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Request request = new Request();
				request.setRequest_id(rs.getInt("REQUEST_ID"));
				request.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				request.setTraining_id(rs.getInt("TRAINING_ID"));
				request.setBenco_id(rs.getInt("BENCO_ID"));
				request.setStatus_id(rs.getInt("STATUS_ID"));
				request.setRequested_reimbursement(rs.getInt("REQUESTED_REIMBURSEMENT"));
				request.setRequested_pass_criteria(rs.getString("REQUESTED_PASS_CRITERIA"));
				request.setRequested_start_date(rs.getString("REQUESTED_START_DATE"));
				request.setRequested_end_date(rs.getString("REQUESTED_END_DATE"));
				request.setJustification(rs.getString("JUSTIFICATION"));
				request.setSupporting_doc(rs.getBlob("SUPPORTING_DOC"));
				return request;
			}
			return null;

		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public boolean updateRequest(Request request) {
		try {
			sql = "UPDATE request SET employee_id = ?, training_id = ?, benco_id = ?, status_id = ?, requested_reimbursement = ?, requested_pass_criteria = ?, requested_start_date = ?,requested_end_date = ?, Justification = ?, supporting_doc = ? WHERE request_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(request.getEmployee_id()));
			ps.setString(2, Integer.toString(request.getTraining_id()));
			ps.setString(3, Integer.toString(request.getBenco_id()));
			ps.setString(4, Integer.toString(request.getStatus_id()));
			ps.setString(5, Integer.toString(request.getRequested_reimbursement()));
			ps.setString(6, request.getRequested_pass_criteria());
			ps.setString(7, request.getRequested_start_date());
			ps.setString(8, request.getRequested_end_date());
			ps.setString(9, request.getJustification());
			ps.setBlob(10, request.getSupporting_doc());
			ps.setString(11, Integer.toString(request.getRequest_id()));
		
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public boolean deleteRequestById(int request_id) {
		try {
			sql = "DELETE request WHERE request_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(request_id));
			
			ps.execute();
			
			return true;

		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

}
