package com.example.lms.model;

import java.util.Date;

public class Admin extends User {

    private String joiningDate;

    public Admin(Long id, String name, String username, String email, String regNo, String mobileNo, String gender, String password, String usertype, String joiningDate) {
        super(id, name, username, email, regNo, mobileNo, gender, password, usertype);
        this.joiningDate = joiningDate;
    }

    public Admin(String name, String username, String email, String regNo, String mobileNo, String gender, String password, String usertype, String joiningDate) {
        super(name, username, email, regNo, mobileNo, gender, password, usertype);
        this.joiningDate = joiningDate;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
}
