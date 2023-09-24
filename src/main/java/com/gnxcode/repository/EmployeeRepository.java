package com.gnxcode.repository;

import com.gnxcode.model.Employee;
import com.gnxcode.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(
            Statement myStamt = getConnection().createStatement();
            ResultSet myRest = myStamt.executeQuery("SELECT * FROM employees")) {

            while (myRest.next()){
              employees.add(createEmployee(myRest));
            }
        }

        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(PreparedStatement myStamt = getConnection().prepareStatement("SELECT * FROM employees WHERE id=?")){
            myStamt.setInt(1, id);
            try(ResultSet resultSet = myStamt.executeQuery()) {
                if(resultSet.next()){
                    employee = createEmployee(resultSet);
                }
            }
        }

        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        if(employee.getId() != null && employee.getId()>0){
            sql = "UPDATE employees SET first_name=? ,pa_surname=?,ma_surname=?,email=?,salary=? WHERE id=?";
        } else {
            sql = "INSERT INTO employees (first_name,pa_surname,ma_surname,email,salary) VALUES (?,?,?,?,?)";
        }
        try (PreparedStatement myStamt = getConnection().prepareStatement(sql)){
            myStamt.setString(1, employee.getFirst_name());
            myStamt.setString(2, employee.getPa_surname());
            myStamt.setString(3, employee.getMa_surname());
            myStamt.setString(4, employee.getEmail());
            myStamt.setFloat(5, employee.getSalary());
            if(employee.getId() != null && employee.getId()>0){
                myStamt.setInt(6, employee.getId());
            }

            myStamt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement myStamt = getConnection().prepareStatement("DELETE FROM employees WHERE id=?")) {
            myStamt.setInt(1,id);
            myStamt.executeUpdate();
        }
    }

    private Employee createEmployee(ResultSet myRest) throws SQLException {
        Employee e = new Employee();
        e.setId(myRest.getInt("id"));
        e.setFirst_name(myRest.getString("first_name"));
        e.setPa_surname(myRest.getString("pa_surname"));
        e.setMa_surname(myRest.getString("ma_surname"));
        e.setEmail(myRest.getString("email"));
        e.setSalary(myRest.getFloat("salary"));
        return e;
    }
}
