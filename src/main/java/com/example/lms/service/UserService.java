package com.example.lms.service;

import com.example.lms.dao.BookDao;
import com.example.lms.dao.UserBookDao;
import com.example.lms.dao.UserDao;
import com.example.lms.model.Book;
import com.example.lms.model.User;
import com.example.lms.model.UserBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserDao userDao;
    private UserBookDao userBookDao;
    private BookDao bookDao ;

    public UserService(UserDao userDao, UserBookDao userBookDao, BookDao bookDao) {
        this.userDao = userDao;
        this.userBookDao = userBookDao;
        this.bookDao = bookDao;
    }

    public User authenticate(String username, String password) {
        return userDao.authenticate(username, password);
    }


    public User findUser(String username){
        return userDao.findOne(username);
    }

    public Collection<User> findAll(){
        return userDao.findAll();
    }

    public User save(User user){
        return userDao.save(user);
    }

    public User update(User user){
        return userDao.update(user);
    }

    public boolean isLibrarian(Long user_id){
      return userDao.isLibrarian(user_id);
    };

    public void issueBook(User user, List<Book> books) {
        userDao.update(user, books);

    }

    public void issueBook(User user, Collection<String> book_name) {
        userDao.update(user, book_name);

    }

    public User findAllBooks(User user) {
        Collection<UserBook> userBooks = userBookDao.findAllByUser(user.getId());

        List<Long> bookIdList = userBooks.stream().map(UserBook::getBookId).collect(Collectors.toList());
        Collection<Book> books = bookDao.findAll(bookIdList);

        user.setBooks(new ArrayList<>(books));

        return user;

    }

    public void addBook(Book book){
        bookDao.save(book);
    }

    public List<Book> findUserBooks(User user){
        return userDao.findUserBooks(user);
    }

    public void returnBook(User user, List<Book> books){
         userDao.deleteUserBook(user, books);
    }
}
