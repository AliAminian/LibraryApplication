package com.example.library.service;

import com.example.library.model.BookEntity;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookEntityServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        List<BookEntity> books = Arrays.asList(new BookEntity(), new BookEntity());
        when(bookRepository.findAll()).thenReturn(books);

        List<BookEntity> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }

    @Test
    void getBookById() {
        Long bookId = 1L;
        BookEntity book = new BookEntity();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Optional<BookEntity> result = bookService.getBookById(bookId);

        assertEquals(book, result);
    }

    @Test
    void createBook() {
        BookEntity book = new BookEntity();
        when(bookRepository.save(any())).thenReturn(book);

        BookEntity result = bookService.createBook(book);

        assertEquals(book, result);
    }

    @Test
    void updateBook() {
        Long bookId = 1L;
        BookEntity existingBook = new BookEntity();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));

        BookEntity updatedBook = new BookEntity();
        when(bookRepository.save(any())).thenReturn(updatedBook);

        Optional<BookEntity> result = bookService.updateBook(bookId, updatedBook);

        assertEquals(updatedBook, result);
    }

    @Test
    void deleteBook() {
        Long bookId = 1L;

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
