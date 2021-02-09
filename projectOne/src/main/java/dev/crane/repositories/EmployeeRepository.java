package dev.crane.repositories;

import java.util.List;

import dev.crane.entities.Employee;

public interface EmployeeRepository {

	// CRUD
	// Create
	boolean newEmployee(Employee employee);
	
	// Read
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employee_id);

	// Update
	boolean updateEmployee(Employee employee);
	
	// Delete
	boolean deleteEmployeeById(int employee_id);
	
}
