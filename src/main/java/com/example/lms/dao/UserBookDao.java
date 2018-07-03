package com.example.lms.dao;

import com.example.lms.model.UserBook;

import java.util.Collection;

public interface UserBookDao {

    UserBook findOne(long userId, long bookId);

    Collection<UserBook> findAllByUser(long userId);

    Collection<UserBook> findAllByBook(long bookId);

    UserBook save(UserBook UserBook);

    UserBook delete(long userId, long bookId);

}
