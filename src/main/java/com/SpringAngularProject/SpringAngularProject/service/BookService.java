package com.SpringAngularProject.SpringAngularProject.service;

import com.SpringAngularProject.SpringAngularProject.model.Book;
import com.SpringAngularProject.SpringAngularProject.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Book saveBook(Book book){
        book.setCreateTime(LocalDateTime.now());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }
}
