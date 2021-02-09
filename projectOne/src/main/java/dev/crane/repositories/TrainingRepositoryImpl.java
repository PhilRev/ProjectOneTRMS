package dev.crane.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.crane.entities.Training;
import dev.crane.util.JDBCConnection;

public class TrainingRepositoryImpl implements TrainingRepository {

	private static Connection conn = JDBCConnection.getConnected();
	private static String sql = "";
	
	public boolean newTraining(Training training) {
		try {
			sql = "CALL add_training(?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			//System.out.println(training);
			cs.setString(1, Integer.toString(training.getGrading_id()));
			cs.setString(2, training.getCourse_name());
			cs.setString(3, training.getCourse_type());
			cs.setString(4, training.getCourse_cost());
			cs.setString(5, training.getStart_date());
			cs.setString(6, training.getEnd_date());
			cs.setString(7, training.getLocation());
			
			cs.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public List<Training> getAllTrainings() {
		List<Training> trainings = new ArrayList<Training>();
		try {
			sql = "SELECT * FROM training";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Training training = new Training();
				training.setTraining_id(rs.getInt("TRAINING_ID"));
				training.setGrading_id(rs.getInt("GRADING_ID"));
				training.setCourse_name(rs.getString("COURSE_NAME"));
				training.setCourse_type(rs.getString("COURSE_TYPE"));
				training.setCourse_cost(rs.getString("COURSE_COST"));
				training.setStart_date(rs.getString("START_DATE"));
				training.setEnd_date(rs.getString("END_DATE"));
				training.setLocation(rs.getString("LOCATION"));
				
				trainings.add(training);				
			}
			return trainings;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public Training getTrainingById(int training_id) {
		try {
			sql = "SELECT * FROM training WHERE training_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(training_id));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Training training = new Training();
				training.setTraining_id(rs.getInt("TRAINING_ID"));
				training.setGrading_id(rs.getInt("GRADING_ID"));
				training.setCourse_name(rs.getString("COURSE_NAME"));
				training.setCourse_type(rs.getString("COURSE_TYPE"));
				training.setCourse_cost(rs.getString("COURSE_COST"));
				training.setStart_date(rs.getString("START_DATE"));
				training.setEnd_date(rs.getString("END_DATE"));
				training.setLocation(rs.getString("LOCATION"));
				
				return training;				
			}
			return null;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public boolean updateTraining(Training training) {
		try {
			sql = "UPDATE training SET grading_id = ?, course_name = ?, course_type = ?, course_cost = ?, start_date = ?, end_date = ?, location = ? WHERE training_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(training.getGrading_id()));
			ps.setString(2, training.getCourse_name());
			ps.setString(3, training.getCourse_type());
			ps.setString(4, training.getCourse_cost());
			ps.setString(5, training.getStart_date());
			ps.setString(6, training.getEnd_date());
			ps.setString(7, training.getLocation());
			ps.setString(8, Integer.toString(training.getTraining_id()));
			
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public boolean deleteTrainingById(int training_id) {
		try {
			sql = "DELETE training WHERE training_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(training_id));
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

}
