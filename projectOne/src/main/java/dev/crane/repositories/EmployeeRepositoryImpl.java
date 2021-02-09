package dev.crane.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.crane.entities.Employee;
import dev.crane.util.JDBCConnection;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	private static Connection conn = JDBCConnection.getConnected();
	private static String sql = "";

	public boolean newEmployee(Employee employee) {
		try {
			sql = "CALL add_employee(?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, employee.getFname());
			cs.setString(2, employee.getLname());
			cs.setString(3, employee.getUsername());
			cs.setString(4, employee.getUserpass());
			cs.setString(5, employee.getEmail());
			cs.setString(6, Integer.toString(employee.getJob_id()));
			cs.setString(7, Integer.toString(employee.getSupervisor_id()));
			
			cs.execute();
			
			return true;					
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	} 

	public List<Employee> getAllEmployees() {
//		Employee one = new Employee(0,"philip","Crane","BigBoss","ssssss","a@b.com",0,0);
//		List<Employee> ons = new ArrayList<Employee>();
//		ons.add(one);
//		return ons; 
		
		List<Employee> employees = new ArrayList<Employee>();
		try {
			sql = "SELECT * FROM employee";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("USERNAME"), rs.getString("USERPASS"), rs.getString("EMAIL"), rs.getInt("JOB_ID"), rs.getInt("SUPERVISOR_ID"));
				employees.add(employee);
			}
			return employees;
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return null;
	}

	public Employee getEmployeeById(int employee_id) {
		try {
			sql = "SELECT * FROM employee WHERE employee_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("USERNAME"), rs.getString("USERPASS"), rs.getString("EMAIL"), rs.getInt("JOB_ID"), rs.getInt("SUPERVISOR_ID"));
				return employee;
			}			

		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return null;
	}

	public boolean updateEmployee(Employee employee) {
		try {
			sql = "UPDATE employee SET fname = ?, lname = ?, username = ?, userpass = ?, email = ?, job_id = ?, supervisor_id = ? WHERE employee_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, employee.getFname());
			ps.setString(2, employee.getLname());
			ps.setString(3, employee.getUsername());
			ps.setString(4, employee.getUserpass());
			ps.setString(5, employee.getEmail());
			ps.setString(6, Integer.toString(employee.getJob_id()));
			ps.setString(7, Integer.toString(employee.getSupervisor_id()));
			ps.setString(8, Integer.toString(employee.getEmployee_id()));
			
			ps.execute();
			
			return true;				
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

	public boolean deleteEmployeeById(int employee_id) {
		try {
			sql = "DELETE employee WHERE employee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(employee_id));
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();	
		}
		return false;
	}

}
