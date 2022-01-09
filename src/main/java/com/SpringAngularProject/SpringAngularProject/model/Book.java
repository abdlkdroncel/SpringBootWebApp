package com.SpringAngularProject.SpringAngularProject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="book")
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title",nullable = false,length = 50)
    private String title;

    @Column(name="description",nullable = false,length = 100)
    private String description;

    @Column(name="author",nullable = false,length = 100)
    private String author;

    @Column(name="price",nullable = false)
    private Double price;

    @Column(name="createTime",nullable = false)
    private LocalDateTime createTime;


}
