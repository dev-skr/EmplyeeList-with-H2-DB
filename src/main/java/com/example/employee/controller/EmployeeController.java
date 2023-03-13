package com.example.employee.controller;

 import org.springframework.web.bind.annotation.*;
 import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.employee.service.EmployeeH2Service;
import com.example.employee.model.Employee;
@RestController

public class EmployeeController{

@Autowired
public EmployeeH2Service service;

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees(){
    return service.getEmployees();
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee){
        return service.postEmployee(employee);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int id){
        return service.getEmployee(id);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee modifyEmployee(@PathVariable("employeeId") int id,@RequestBody Employee employee){
        return service.putEmployee(id,employee);
    }
    
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable("employeeId") int id){
        service.deleteEmployee(id);
    }
    
}
