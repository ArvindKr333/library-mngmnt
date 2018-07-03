package com.example.lms.dao;

import com.example.lms.model.Book;
import com.example.lms.model.User;
import com.example.lms.utils.CSVUtils;
import com.example.lms.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MySQLBookDaoImpl implements BookDao {
    @Override
    public Book findOne(int id) {
        Book book = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT * from book where book_id = '" + id + "'";
        try {

            PreparedStatement pstd = connection.prepareStatement(sql);

            ResultSet rs = pstd.executeQuery();
            if (rs.next()) {
                book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    @Override
    public Book findOne(String name) {
        Book book = null;
        Connection connection = ConnectionUtils.getConnection();
        String sql = "SELECT * from book WHERE name = '" + name + "'";
        try {

            PreparedStatement pstd = connection.prepareStatement(sql);

            ResultSet rs = pstd.executeQuery();
            if (rs.next()) {
                book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    @Override
    public List<Book> findBooks(Collection<String> bookNames) {
        List<Book> books = new ArrayList<>();
        Book book = null;
        String sql = "SELECT * FROM book WHERE name IN( "+ CSVUtils.getCSV(bookNames.stream().collect(Collectors.toList()))+" )";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);

            ResultSet rs = pstd.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public List<Book> findBooks(List<Long> bookIDs) {
        List<Book> books = new ArrayList<>();
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id IN("+ CSVUtils.getCSV(bookIDs.stream().map(id -> String.valueOf(id)).collect(Collectors.toList()))+" )";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }


    @Override
    public Collection<Book> findAll() {
        String sql = "SELECT * FROM book";
        Collection<Book> books = new ArrayList<>();
        Book book = null;
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();
            while (rs.next()) {
                book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Collection<Book> findAll(List<Long> idList) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        Connection connection = ConnectionUtils.getConnection();
        try {

            PreparedStatement pstd = connection.prepareStatement(sql);
            ResultSet rs = pstd.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String edition = rs.getString(3);
                String publisher = rs.getString(4);
                String language = rs.getString(5);
                String category = rs.getString(6);
                Book book = new Book(name, edition, publisher, language, category);
                books.add(book);
            }
            books.stream().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO book VALUES(NULL, ?,?,?,?,?)";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.setString(1, book.getName());
            pstd.setString(2, book.getEdition());
            pstd.setString(3, book.getPublisher());
            pstd.setString(4, book.getLanguage());
            pstd.setString(5, book.getCategory());

            int rs = pstd.executeUpdate();
            if (rs == 1) {
                Book b = findOne(book.getName());
                book.setId(b.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    @Override
    public Book update(Book book) {
        Book b = findOne(book.getName());
        String sql = "UPDATE book SET name = ?, edition = ?, publisher = ?, language = ?, category = ? WHERE book_id = '" + b.getId() + "' ";
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement pstd = connection.prepareStatement(sql);
            pstd.setString(1, book.getName());
            pstd.setString(2, book.getEdition());
            pstd.setString(3, book.getPublisher());
            pstd.setString(4, book.getLanguage());
            pstd.setString(5, book.getCategory());

            int rs = pstd.executeUpdate();
            if (rs == 1) {
                Book b1 = findOne(book.getName());
                book.setId(b1.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    @Override
    public Book delete(int id) {
        Book book = findOne(id);
        if (book != null) {
            Connection connection = ConnectionUtils.getConnection();
            String sql = "DELETE from book WHERE book_id = '" + id + "' ";
            try {
                PreparedStatement pstd = connection.prepareStatement(sql);
                pstd.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return book;
        } else {
            return null;
        }

    }
}
