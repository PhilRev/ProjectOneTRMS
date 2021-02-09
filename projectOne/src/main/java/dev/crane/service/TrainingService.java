package dev.crane.service;

import java.util.List;

import dev.crane.entities.Employee;
import dev.crane.entities.Grading;
import dev.crane.entities.Jobs;
import dev.crane.entities.Request;
import dev.crane.entities.Status;
import dev.crane.entities.Training;

public interface TrainingService {
	
	boolean newHire(Employee employee);
	boolean newCourse(Training training);
	boolean newJob(Jobs job);
	boolean newRequest(Request request);
	boolean newStatus(Status status);
	boolean newGrading(Grading grade);
	
	List<Employee> allEmployees();
	Employee getEmployeeById(int employee_id);
	boolean updateEmployee(Employee employee);
	
	List<Training> allCourses();
	Training getCourseById(int training_id);
	
	List<Jobs> allJobs();
	Jobs getJobById(int job_id);
	
	List<Request> allRequests();
	Request getRequestById(int request_id);
	
	List<Status> allStatuses();
	Status getStatusById(int status_id);
	
	List<Grading> allGradings();
	Grading getGradingById(int grading_id);
	
	boolean setPassword(Employee employee);	
	
	boolean updateTraining(Training training);
	
	boolean updateStatus(Status status);
	
	boolean updateRequest(Request request);
	
	boolean deleteRequestById(int request_id);

}
