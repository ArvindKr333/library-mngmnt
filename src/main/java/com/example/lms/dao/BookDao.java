package com.example.lms.dao;

import com.example.lms.model.Book;
import java.util.Collection;
import java.util.List;

public interface BookDao {

    Book findOne(int id);

    Book findOne(String name);

    List<Book> findBooks(Collection<String> bookNames);

    List<Book> findBooks(List<Long> bookIDs);

    Collection<Book> findAll();

    Collection<Book> findAll(List<Long> idList);

    Book save(Book book);

    Book update(Book book);

    Book delete(int id);

}
