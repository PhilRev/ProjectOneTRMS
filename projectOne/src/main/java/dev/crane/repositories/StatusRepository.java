package dev.crane.repositories;

import java.util.List;

import dev.crane.entities.Status;

public interface StatusRepository {

	// CRUD
	// Create
	boolean newStatus(Status status);
	
	// Read
	List<Status> getAllStatuses();
	Status getStatusById(int status_id);
	
	// Update
	boolean updateStatus(Status status);
	
	// Delete
	boolean deleteStatusById(int status_id);	
	
}
