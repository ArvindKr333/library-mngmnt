package com.example.lms;

import com.example.lms.dao.MySQLBookDaoImpl;
import com.example.lms.dao.MySQLUserDaoImpl;
import com.example.lms.model.*;
import com.example.lms.utils.CSVUtils;
import com.example.lms.utils.ConnectionUtils;
import com.example.lms.utils.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestSqlConnection {

    public static void main(String[] args) {

//        ConnectionUtils con = new ConnectionUtils();
//        con.getConnection();
        //MySQLUserDaoImpl mySQLUserDao = new MySQLUserDaoImpl();
        //mySQLUserDao.save(new Student("Amy", "AmyJack", "AmyJack123@gmail.com", "12321/12", "9092342345", "M", "amy123", "STUDENT", "CSE", "12", 132164));
        //mySQLUserDao.save(new Admin("Raj", "Raj123", "Raj123@gmail.com", "12332/12", "34334523467", "M", "raj123", "ADMIN", "2011-10-11"));
        //mySQLUserDao.save(new Teacher("Abhi", "Abhi", "abhi.789@gmail.com", "12124/12", "9099092302", "M", "abhi1234", "TEACHER","ELECTRICAL", "2013-11-12", Boolean.FALSE));
        //mySQLUserDao.findAll();
        //mySQLUserDao.delete(2);
        /*MySQLBookDaoImpl mySQLBookDao = new MySQLBookDaoImpl();
        //Book book = new Book("Ele","6","jjG","ARB","Sci");
        //mySQLBookDao.update(book);
        mySQLBookDao.delete(2);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
*/


        MySQLUserDaoImpl mySQLUserDao = new MySQLUserDaoImpl();
        mySQLUserDao.findUserBooks(mySQLUserDao.findOne(1));

        List<Book> list = new ArrayList<>();
        //list.add(new Book(1L, "", "","","",""));
        list.add(new Book(4L, "", "","","",""));
        list.add(new Book(7L, "", "","","",""));

        List<Long> bookIdList = Arrays.asList(1L, 4L);

        MySQLBookDaoImpl mySQLBookDao = new MySQLBookDaoImpl();
        mySQLBookDao.findBooks(bookIdList);

        User  user = mySQLUserDao.findOne(1);
        mySQLUserDao.deleteUserBook(user,list);

        Connection connection = ConnectionUtils.getConnection();



        mySQLBookDao.findAll();

        //String sql = "INSERT INTO user_book VALUES(?,?)";
//        List<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        StringBuilder sb = new StringBuilder("INSERT INTO user_book VALUES ");
//
//        for (int i = 0; i < list.size(); i++) {
//            sb.append("(5 , "+ list.get(i)+  "),");
//            if (i == list.size() - 1) {
//                sb.setLength(sb.length()-1);
//            }
//        }
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(sb);


    }


}
