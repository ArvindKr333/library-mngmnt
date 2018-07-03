package com.example.lms.dao;

import com.example.lms.model.UserBook;
import com.example.lms.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryUserBookDaoImpl implements UserBookDao {

    private Set<UserBook> userBookCache = new HashSet<>();

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
        List<Long> list = null;
        StringBuilder sb = new StringBuilder("INSERT INTO user_book VALUES ");
        Connection connection = ConnectionUtils.getConnection();
        for (int i = 0; i < list.size(); i++) {
            sb.append("(5 , "+ list.get(i)+  "),");
            if (i == list.size() - 1) {
                sb.setLength(sb.length()-1);
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
            preparedStatement.executeUpdate();

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
