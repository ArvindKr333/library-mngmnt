package com.example.lms.dao;

import com.example.lms.model.Book;
import com.example.lms.model.User;
import java.util.Collection;
import java.util.List;

public interface UserDao {

    User authenticate(String username, String password);

    User findOne(int id);

    User findOne(String username);

    List<Book> findUserBooks(User user);

    Collection<User> findAll();

    User save(User user);

    User update(User user);

    User update(User user, List<Book> books);

    User update(User user, Collection<String> book_name);

    User delete(int id);

    User delete(String username);

    Boolean isLibrarian(Long user_id);

    void deleteUserBook(User user, List<Book> books);


}