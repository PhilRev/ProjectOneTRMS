package dev.crane.service;

import java.util.List;

import dev.crane.entities.Employee;
import dev.crane.entities.Grading;
import dev.crane.entities.Jobs;
import dev.crane.entities.Request;
import dev.crane.entities.Status;
import dev.crane.entities.Training;
import dev.crane.repositories.EmployeeRepository;
import dev.crane.repositories.EmployeeRepositoryImpl;
import dev.crane.repositories.GradingRepository;
import dev.crane.repositories.GradingRepositoryImpl;
import dev.crane.repositories.JobsRepository;
import dev.crane.repositories.JobsRepositoryImpl;
import dev.crane.repositories.RequestRepository;
import dev.crane.repositories.RequestRepositoryImpl;
import dev.crane.repositories.StatusRepository;
import dev.crane.repositories.StatusRepositoryImpl;
import dev.crane.repositories.TrainingRepository;
import dev.crane.repositories.TrainingRepositoryImpl;

public class TrainingServiceImpl implements TrainingService {
	
	EmployeeRepository er = new EmployeeRepositoryImpl();
	GradingRepository gr = new GradingRepositoryImpl();
	JobsRepository jr = new JobsRepositoryImpl();
	RequestRepository rr = new RequestRepositoryImpl();
	StatusRepository sr = new StatusRepositoryImpl();
	TrainingRepository tr = new TrainingRepositoryImpl();

	public boolean newHire(Employee employee) {
		return er.newEmployee(employee);
	}

	public boolean newCourse(Training training) {
		return tr.newTraining(training);
	}

	public boolean newJob(Jobs job) {
		
		return jr.newJob(job);
	}

	public boolean newRequest(Request request) {
		return rr.newRequest(request);
	}

	public boolean newStatus(Status status) {
		return sr.newStatus(status);
	}

	public boolean newGrading(Grading grade) {
		return gr.newGrade(grade);
	}

	public List<Employee> allEmployees() {
		return er.getAllEmployees();
	}

	public Employee getEmployeeById(int employee_id) {
		return er.getEmployeeById(employee_id);
	}
	
	public boolean updateEmployee(Employee employee) {
		return er.updateEmployee(employee);
	}

	public List<Training> allCourses() {
		return tr.getAllTrainings();
	}

	public Training getCourseById(int training_id) {
		System.out.println(training_id);
		return tr.getTrainingById(training_id);
	}

	public List<Jobs> allJobs() {
		return jr.getAllJobs();
	}

	public Jobs getJobById(int job_id) {
		return jr.getJobById(job_id);
	}

	public List<Request> allRequests(){
		return rr.getAllRequests();
	}
	
	public Request getRequestById(int request_id) {
		return rr.getRequestById(request_id);
	}
	
	public List<Status> allStatuses() {
		return sr.getAllStatuses();
	}

	public Status getStatusById(int status_id) {
		return sr.getStatusById(status_id);
	}

	public List<Grading> allGradings() {
		return gr.getAllGradings();
	}

	public Grading getGradingById(int grading_id) {
		return gr.getGradeById(grading_id);
	}

	public boolean setPassword(Employee employee) {
		return er.updateEmployee(employee);
	}

	@Override
	public boolean updateTraining(Training training) {
		return tr.updateTraining(training);
	}

	@Override
	public boolean updateStatus(Status status) {
		return sr.updateStatus(status);
	}

	@Override
	public boolean updateRequest(Request request) {
		return rr.updateRequest(request);
	}

	public boolean deleteRequestById(int request_id) {
		return rr.deleteRequestById(request_id);
	}
}
