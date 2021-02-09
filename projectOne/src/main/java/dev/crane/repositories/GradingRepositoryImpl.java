package dev.crane.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.crane.entities.Grading;
import dev.crane.util.JDBCConnection;

public class GradingRepositoryImpl implements GradingRepository {

	private static Connection conn = JDBCConnection.getConnected();
	private static String sql = "";
	
	public boolean newGrade(Grading grade) {
		try {
			sql = "CALL add_grading(?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, grade.getPass_criteria());
			
			cs.execute();
			
			return true;
			
		}  catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

	public List<Grading> getAllGradings() {
		List<Grading> gradings = new ArrayList<Grading>();
		try {
			sql = "SELECT * FROM grading";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Grading grade = new Grading(rs.getInt("GRADING_ID"), rs.getString("PASS_CRITERIA"));
				gradings.add(grade);
			}
			return gradings;
			
		}  catch (SQLException e) {
			e.getStackTrace();	
		}
		return null;
	}

	public Grading getGradeById(int grading_id) {
		try {
			sql = "SELECT * FROM grading WHERE grading_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, grading_id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Grading grade = new Grading(rs.getInt("GRADING_ID"), rs.getString("PASS_CRITERIA"));
				return grade;
			}
		}  catch (SQLException e) {
			e.getStackTrace();	
		}
		return null;
	}

	public boolean updateGrade(Grading grade) {
		try {
			sql = "UPDATE grading SET pass_criteria = ? WHERE grading_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, grade.getPass_criteria());
			ps.setString(2, Integer.toString(grade.getGrading_id()));
			
			ps.execute();
			
			return true;
			
		}  catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

	public boolean deleteGradeById(int grading_id) {
		try {
			sql = "DELETE grading WHERE grading_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, grading_id);
			ps.execute();
			return true;
			
		}  catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

}
