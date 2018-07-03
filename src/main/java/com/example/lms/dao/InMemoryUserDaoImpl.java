package com.example.lms.dao;

import com.example.lms.model.Admin;
import com.example.lms.model.Book;
import com.example.lms.model.User;
import com.example.lms.model.UserBook;
import com.example.lms.utils.DateUtils;

import java.util.*;

public class InMemoryUserDaoImpl implements UserDao {

    private Map<Long, User> userCache = new HashMap<>();
    private Long counter;

    public InMemoryUserDaoImpl() {
        counter = 1L;

        User user = new Admin(counter++, "Arvind", "Arvind123", "arvind@gmail.com",
                "15184", "8700553500", "M", "arvind","ADMIN", "1998-11-12");
        userCache.put(user.getId(), user);


    }

    @Override
    public User authenticate(String username, String password) {
        User user = findOne(username);
        if (user.getPassword().equals(password) && user != null) {
            return user;
        } else {
            return null;
        }
    }

    public User findOne(int id) {
        return userCache.values().stream().filter(user -> user.getId().equals(id)).findAny().orElse(null);
    }

    public User findOne(String username) {
        User user = userCache.values().stream().filter(person -> person.getUsername().equals(username)).findAny().orElse(null);
        return user;
    }

    @Override
    public List<Book> findUserBooks(User user) {
        return null;
    }

    public Collection<User> findAll() {
        return userCache.values();
    }

    public User save(User user) {
        user.setId(counter++);
        userCache.put(user.getId(), user);
        return user;
    }

    public User update(User user) {
        return null;
    }

    @Override
    public User update(User user, List<Book> books) {
        return null;
    }

    @Override
    public User update(User user, Collection<String> book_name) {
        return null;
    }

    public User delete(int id) {
        User user = findOne(id);
        if (user != null) {
            userCache.remove(user.getId());
        }
        return user;
    }

    public User delete(String username) {
        User user = findOne(username);
        if (user != null) {
            userCache.remove(user.getId());
        }
        return user;
    }

    @Override
    public Boolean isLibrarian(Long user_id) {
        return null;
    }

    @Override
    public void deleteUserBook(User user, List<Book> books) {

    }

    public Map<Long, User> getUserCache() {
        return userCache;
    }

}
