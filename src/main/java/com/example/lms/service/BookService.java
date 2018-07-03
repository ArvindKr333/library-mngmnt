package com.example.lms.service;

import com.example.lms.dao.BookDao;
import com.example.lms.model.Book;

import java.util.Collection;

public class BookService {

    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book save(Book book){
        return bookDao.save(book);
    }

    public Collection<Book> findAll(){
        return bookDao.findAll();
    }

}
