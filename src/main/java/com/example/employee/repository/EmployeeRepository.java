package com.example.employee.repository;
import java.util.ArrayList;
import com.example.employee.model.Employee;
public interface EmployeeRepository{
ArrayList<Employee> getEmployees();
Employee postEmployee(Employee employee);
Employee putEmployee(int id,Employee employee);
Employee getEmployee(int id);

void deleteEmployee(int id);

}