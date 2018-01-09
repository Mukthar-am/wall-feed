package com.data.touchstone.wallfeed.dao;

/**
 * Author: mukthar.m@myntra.com
 * Date: 2017-12-27
 */

import com.data.touchstone.wallfeed.representations.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDB {
    public static HashMap<Integer, Employee> employees = new HashMap<>();
    private static int totalEmployes = 3;

    static{
        employees.put(1, new Employee(1, "Lokesh", "Gupta", "India"));
        employees.put(2, new Employee(2, "John", "Gruber", "USA"));
        employees.put(3, new Employee(3, "Melcum", "Marshal", "AUS"));
    }

    public static List<Employee> getEmployees(){
        return new ArrayList<>(employees.values());
    }

    public static Employee getEmployee(Integer id){
        return employees.get(id);
    }

    public static void updateEmployee(Integer id, Employee employee){
        employees.put(id, employee);
    }

    public static void removeEmployee(Integer id){
        employees.remove(id);
    }

    public static int addEmployee(Employee employee) {
        System.out.println("Current Total: " + totalEmployes);
        int newEmpId = ++totalEmployes;
        System.out.println("New EmpID: " + newEmpId);

        employee.setId(newEmpId);
        employees.put(newEmpId, employee);
        return newEmpId;
    }
}