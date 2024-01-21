package com.example.library.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    private String title;
    private String author;
    private int quantity;
}
