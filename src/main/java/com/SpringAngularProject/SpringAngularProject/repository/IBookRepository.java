package com.SpringAngularProject.SpringAngularProject.repository;

import com.SpringAngularProject.SpringAngularProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Long> {

}
