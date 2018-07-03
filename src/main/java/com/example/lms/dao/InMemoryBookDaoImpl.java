package com.example.lms.dao;

import com.example.lms.model.Book;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookDaoImpl implements BookDao {

    private Map<Long, Book> bookCache = new HashMap<>();
    private Long counter;

    public InMemoryBookDaoImpl() {
        counter = 1L;
        Book book = new Book(counter++, "Java - The Complete Reference ", "7th Edition", "Mcgraw Higher Ed", "English", "Computer & Internet");
        bookCache.put(book.getId(), book);
    }

    public Book findOne(int id) {
        return bookCache.values().stream().filter(book -> book.getId().equals(id)).findAny().orElse(null);
    }

    public Book findOne(String name) {
        return bookCache.values().stream().filter(book -> book.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public List<Book> findBooks(Collection<String> bookNames) {
        return null;
    }

    @Override
    public List<Book> findBooks(List<Long> bookIDs) {
        return null;
    }

    public Collection<Book> findAll() {
        return bookCache.values();
    }

    @Override
    public Collection<Book> findAll(List<Long> idList) {
        return null;
    }

    public Book save(Book book) {
        book.setId(counter++);
        bookCache.put(book.getId(), book);
        return book;
    }

    public Book update(Book book) {
        return null;
    }

    public Book delete(int id) {
        return null;
    }
}
