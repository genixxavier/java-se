package com.gnxcode;

import com.gnxcode.model.Employee;
import com.gnxcode.repository.EmployeeRepository;
import com.gnxcode.repository.Repository;
import com.gnxcode.util.DatabaseConnection;
import com.gnxcode.view.SwingApp;

import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main2(String[] args) throws SQLException {

        try(Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> employeeRepository = new EmployeeRepository();

            System.out.println("----List employee ----");
            employeeRepository.findAll().forEach(System.out::println);

            //System.out.println(employeeRepository.getById(4));
            /*System.out.println("-----Insert employee-----");
            Employee employee = new Employee();
            employee.setId(7);
            employee.setFirst_name("Diego");
            employee.setPa_surname("Maradona");
            employee.setMa_surname("Pimentel");
            employee.setEmail("elmaradona@example.com");
            employee.setSalary((float) 2000);
            employeeRepository.save(employee);*/

            System.out.println("----employee deleted----");
            employeeRepository.delete(6);

            System.out.println("----List employee reload ----");
            employeeRepository.findAll().forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        SwingApp app = new SwingApp();
        app.setVisible(true);
    }
}