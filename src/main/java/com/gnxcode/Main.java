package com.gnxcode;

import com.gnxcode.model.Employee;
import com.gnxcode.repository.EmployeeRepository;
import com.gnxcode.repository.Repository;
import com.gnxcode.util.DatabaseConnection;
//import com.gnxcode.view.SwingApp;

import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main2(String[] args) throws SQLException {

        try(Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> employeeRepository = new EmployeeRepository(myConn);

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

    /*public static void main3(String[] args) {
        SwingApp app = new SwingApp();
        app.setVisible(true);
    }*/

    public static void main(String[] args) throws SQLException {
        try(Connection connection = DatabaseConnection.getInstance()) {
            if(connection.getAutoCommit()){
                connection.setAutoCommit((false));
            }

            try {
                Repository<Employee> employeeRepository = new EmployeeRepository(connection);

                System.out.println("-----Insert employee-----");
                Employee employee = new Employee();
                /*employee.setFirst_name("Armando");
                employee.setPa_surname("Elias");
                employee.setMa_surname("Almo");
                employee.setEmail("armando@example.com");
                employee.setSalary((float) 2000);
                employee.setCurp("QWE12343AS322123");
                employeeRepository.save(employee);
                connection.commit();*/

                employee.setFirst_name("David");
                employee.setPa_surname("Gutiereex");
                employee.setMa_surname("Diaz");
                employee.setEmail("david@example.com");
                employee.setSalary((float) 2000);
                employee.setCurp("QWE12343AS322123");
                employeeRepository.save(employee);
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}