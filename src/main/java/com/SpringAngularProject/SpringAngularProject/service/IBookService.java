package com.SpringAngularProject.SpringAngularProject.service;

import com.SpringAngularProject.SpringAngularProject.model.Book;

import java.util.List;

public interface IBookService {
    Book saveBook(Book book);
    void deleteBook(Long bookId);
    List findAllBook();
}
