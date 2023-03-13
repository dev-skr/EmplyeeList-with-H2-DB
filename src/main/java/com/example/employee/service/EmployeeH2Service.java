package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
 import com.example.employee.repository.EmployeeRepository;
 import com.example.employee.model.EmployeeRowMapper;
 import com.example.employee.model.Employee;

@Service
public class EmployeeH2Service implements EmployeeRepository{

@Autowired
public JdbcTemplate db;

@Override
    public ArrayList<Employee> getEmployees(){
        List<Employee> list= db.query("select * from employeelist", new EmployeeRowMapper());
        ArrayList<Employee> employees=new ArrayList<>(list);
        return employees;
    }

    @Override
    public Employee postEmployee(Employee employee){
       db.update("insert into employeelist(employeeName,email,department) values(?,?,?)",
       employee.getEmployeeName(),employee.getEmail(), employee.getDepartment());

    try{
       Employee existingEmployee = db.queryForObject("select * from employeelist where employeeName=?",
       new EmployeeRowMapper(),employee.getEmployeeName());

       return existingEmployee;

    }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

        
    }

    @Override
    public Employee getEmployee(int id){
       try{
       Employee existingEmployee= db.queryForObject("select * from employeelist where employeeId=?",
       new EmployeeRowMapper(),id);
       
       return existingEmployee;
    }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    }

    @Override
    public Employee putEmployee(int id, Employee employee){
        
        Employee existingEmployee= getEmployee(id);
        
        if(employee.getEmployeeName()!=null)
        existingEmployee.setEmployeeName(employee.getEmployeeName());

        if(employee.getEmail()!=null)
       existingEmployee.setEmail(employee.getEmail());

        if(employee.getDepartment()!=null)
    existingEmployee.setDepartment(employee.getDepartment());


    db.update("update employeelist set employeeName=?, email=?, department=? where employeeId=?",
    existingEmployee.getEmployeeName(),existingEmployee.getEmail(),existingEmployee.getDepartment(),id);
       
       
    return existingEmployee;

    }

    @Override
    public void deleteEmployee(int id){
       
       db.update("delete from employeelist where employeeId=?",id);

    
    }



}
