package com.example.lms.model;

import java.util.List;

public class Student extends User {

    private String branch;
    private String  batch;
    private Integer rollNo;

//    private List<Book> books;


    public Student(Long id, String name, String username, String email, String regNo, String mobileNo, String gender, String password,String usertype, String branch, String batch, Integer rollNo) {
        super(id, name, username, email, regNo, mobileNo, gender, password, usertype);
        this.branch = branch;
        this.batch = batch;
        this.rollNo = rollNo;
    }

    public Student(String name, String username, String email, String regNo, String mobileNo, String gender, String password, String usertype, String branch, String batch, Integer rollNo) {
        super(name, username, email, regNo, mobileNo, gender, password, usertype);
        this.branch = branch;
        this.batch = batch;
        this.rollNo = rollNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
}
