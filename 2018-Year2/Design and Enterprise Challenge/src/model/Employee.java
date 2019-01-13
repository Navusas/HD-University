package model;

import java.util.Arrays;

/**
 * @author Domantas Giedraitis - Navus
 * @version 1.001
 **/

public class Employee {
    private int employeeID;
    private String name;
    private String surname;
    private int workGroup;
    private int warehouse;
    private long dateStarted;
    private String dateResigned;
    private String currentTask = "Nothing";
    private String taskActiveFor = "00:00";
    private int privilleges;

//    Software related
    protected String username;
    protected String password;

    public Employee(int employeeID, String name, String surname, int workGroup, int warehouse, long dateStarted, String username, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.workGroup = workGroup;
        this.warehouse = warehouse;
        this.dateStarted = dateStarted;
        this.username = username;
        this.password = password;
        this.dateResigned = null;
    }

    public Employee(int employeeID, String name, String surname, String username, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.workGroup = 0;
        this.warehouse = 0;
        this.dateStarted = System.currentTimeMillis();
        this.dateResigned = null;

    }

    public Employee(String username, String password) {
        this.employeeID = 0;
        this.name = null;
        this.surname =null;
        this.workGroup = 0;
        this.warehouse = 0;
        this.dateStarted = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.dateResigned = null;
    }
    public Employee(String username, String password, int workGroup, int privilleges) {
        this.employeeID = 0;
        this.name = null;
        this.surname =null;
        this.workGroup = workGroup;
        this.privilleges = privilleges;
        this.warehouse = 0;
        this.dateStarted = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.dateResigned = null;
    }
    public Employee(String username, String password, int workGroup) {
        this.employeeID = 0;
        this.name = null;
        this.surname =null;
        this.workGroup = workGroup;
        this.warehouse = 0;
        this.dateStarted = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.dateResigned = null;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(int workGroup) {
        this.workGroup = workGroup;
    }

    public int getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(int warehouse) {
        this.warehouse = warehouse;
    }

    public long getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(long dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getDateResigned() {
        return dateResigned;
    }

    public void setDateResigned(String dateResigned) {
        this.dateResigned = dateResigned;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public String getTaskActiveFor() {
        return taskActiveFor;
    }

    public void setTaskActiveFor(String taskActiveFor) {
        this.taskActiveFor = taskActiveFor;
    }

    public int getPrivilleges() {
        return privilleges;
    }

    public void setPrivilleges(int privilleges) {
        this.privilleges = privilleges;
    }
}
