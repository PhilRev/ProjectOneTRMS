package dev.crane.repositories;

import java.util.List;

import dev.crane.entities.Jobs;

public interface JobsRepository {

	// CRUD
	// Create
	boolean newJob(Jobs job);
	
	// Read
	List<Jobs> getAllJobs();
	Jobs getJobById(int job_id);
	
	// Update
	boolean updateJob(Jobs job);
	
	// Delete 
	boolean deleteJobById(int job_id);
}
