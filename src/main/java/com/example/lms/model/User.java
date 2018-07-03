package com.example.lms.model;

import com.example.lms.UserType; //Admin, Librarian, Teacher, Student

import java.util.List;

public class User {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String regNo;
    private String mobileNo;
    private String gender;
    private String password;
    private String userType;


    private List<Book> books;

    protected User(Long id, String name, String username, String email, String regNo, String mobileNo, String gender, String password, String userType) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.regNo = regNo;
        this.mobileNo = mobileNo;
        this.gender = gender;
        this.password = password;
        this.userType = userType;
    }

    protected User(String name, String username, String email, String regNo, String mobileNo, String gender, String password, String userType) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.regNo = regNo;
        this.mobileNo = mobileNo;
        this.gender = gender;
        this.password = password;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", regNo=" + regNo +
                ", mobileNo=" + mobileNo +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
