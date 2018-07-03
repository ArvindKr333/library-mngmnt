package com.example.lms.dao;

import com.example.lms.model.UserBook;
import com.example.lms.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class MySQLUserBookDaoImpl implements UserBookDao{
    @Override
    public UserBook findOne(long userId, long bookId) {
        return null;
    }

    @Override
    public Collection<UserBook> findAllByUser(long userId) {
        return null;
    }

    @Override
    public Collection<UserBook> findAllByBook(long bookId) {
        return null;
    }

    @Override
    public UserBook save(UserBook UserBook) {
        //List<Book>
        String sql = "INSERT INTO user_book values (?, ?)";


        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserBook delete(long userId, long bookId) {
        return null;
    }
}
