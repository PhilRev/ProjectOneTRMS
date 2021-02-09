package dev.crane.repositories;

import java.util.List;

import dev.crane.entities.Request;

public interface RequestRepository {

	// CRUD
	// Create
	boolean newRequest(Request request);
	
	// Read
	List<Request> getAllRequests();
	Request getRequestById(int request_id);
	
	// Update
	boolean updateRequest(Request request);
	
	// Delete
	boolean deleteRequestById(int request_id);
		
}
