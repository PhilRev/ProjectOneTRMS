package dev.crane.repositories;

import java.util.List;

import dev.crane.entities.Grading;

public interface GradingRepository {

	// CRUD
	// Create
	boolean newGrade(Grading grade);
	
	// Read
	List<Grading> getAllGradings();
	Grading getGradeById(int grading_id);
	
	// Update
	boolean updateGrade(Grading grade);
	
	// Delete 
	boolean deleteGradeById(int grading_id);

}
