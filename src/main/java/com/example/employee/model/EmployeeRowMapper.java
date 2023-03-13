package com.example.employee.model;

import org.springframework.jdbc.core.RowMapper;
 import java.sql.ResultSet;
 import java.sql.SQLException;

 public class EmployeeRowMapper implements RowMapper<Employee>{
    public Employee mapRow(ResultSet rs, int rowNumber) throws SQLException{
        return new Employee(
            rs.getInt("employeeId"),
            rs.getString("employeeName"),
            rs.getString("email"),
            rs.getString("department")
            );

    }
}