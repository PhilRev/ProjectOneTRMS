package dev.crane.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.crane.entities.Jobs;
import dev.crane.util.JDBCConnection;

public class JobsRepositoryImpl implements JobsRepository {
	
	private static Connection conn = JDBCConnection.getConnected();
	private static String sql = "";

	public boolean newJob(Jobs job) {
		try {
			sql = "CALL add_job(?,?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, job.getJob_des());
			cs.setString(2, Integer.toString(job.getDep_head_id()));

			cs.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

	public List<Jobs> getAllJobs() {
		List<Jobs> jobs = new ArrayList<Jobs>();
		try {
			sql = "SELECT * FROM jobs";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			Jobs job = new Jobs(rs.getInt("JOB_ID"), rs.getString("JOB_DES"), rs.getInt("DEP_HEAD_ID"));
			jobs.add(job);
			}
			return jobs;
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return null;
	}

	public Jobs getJobById(int job_id) {
		try {
			sql = "SELECT * FROM jobs WHERE job_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, job_id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Jobs job = new Jobs(rs.getInt("JOB_ID"), rs.getString("JOB_DES"), rs.getInt("DEP_HEAD_ID"));
				return job;
			}
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return null;
	}

	public boolean updateJob(Jobs job) {
		try {
			sql = "UPDATE jobs SET job_des = ?, dep_head_id = ? WHERE job_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, job.getJob_des());
			ps.setString(2, Integer.toString(job.getDep_head_id()));
			ps.setString(3, Integer.toString(job.getJob_id()));
			
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

	public boolean deleteJobById(int job_id) {
		try {
			sql = "DELETE jobs WHERE job_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, job_id);
			
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

}
