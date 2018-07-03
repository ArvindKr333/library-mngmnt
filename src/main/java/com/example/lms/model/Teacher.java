package com.example.lms.model;

import java.util.Date;

public class Teacher extends User {

    private String department;
    private String joiningDate;
    private Boolean isLibrarian;

    public Teacher(Long id, String name, String username, String email, String regNo, String mobileNo, String gender, String password,String usertype, String department, String joiningDate, Boolean isLibrarian) {
        super(id, name, username, email, regNo, mobileNo, gender, password, usertype);
        this.department = department;
        this.joiningDate = joiningDate;
        this.isLibrarian = isLibrarian;
    }

    public Teacher(String name, String username, String email, String regNo, String mobileNo, String gender, String password,String usertype, String department, String joiningDate, Boolean isLibrarian) {
        super(name, username, email, regNo, mobileNo, gender, password, usertype);
        this.department = department;
        this.joiningDate = joiningDate;
        this.isLibrarian = isLibrarian;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Boolean getLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(Boolean librarian) {
        isLibrarian = librarian;
    }

}
