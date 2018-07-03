package com.example.lms.model;

public class UserBook {
    private Long userId;
    private Long bookId;

    public UserBook() {
    }

    public UserBook(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBook userBook = (UserBook) o;

        if (!userId.equals(userBook.userId)) return false;
        return bookId.equals(userBook.bookId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + bookId.hashCode();
        return result;
    }
}
