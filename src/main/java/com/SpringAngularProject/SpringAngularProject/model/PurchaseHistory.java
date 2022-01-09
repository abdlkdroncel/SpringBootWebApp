package com.SpringAngularProject.SpringAngularProject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="PurchaseHistoryService")
@Data
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id",nullable = false)
    private long userId;

    @Column(name="book_id",nullable = false)
    private long bookId;

    @Column(name="price",nullable = true)
    private double price;

    @Column(name="purchase_time",nullable = false)
    private LocalDateTime purchaseTime;


}
