package com.example.library.service;

import com.example.library.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookEntity> getAllBooks();

    Optional<BookEntity> getBookById(Long id);

    BookEntity createBook(BookEntity bookEntity);

    Optional<BookEntity> updateBook(Long id, BookEntity updatedBookEntity);

    void deleteBook(Long id);
}
