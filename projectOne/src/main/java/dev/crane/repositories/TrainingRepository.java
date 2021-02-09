package dev.crane.repositories;

import java.util.List;

import dev.crane.entities.Training;

public interface TrainingRepository {

	// CRUD
	// Create
	boolean newTraining(Training training);
	
	// Read
	List<Training> getAllTrainings();
	Training getTrainingById(int training_id);
	
	// Update
	boolean updateTraining(Training training);
	
	// Delete
	boolean deleteTrainingById(int training_id);
	
}
