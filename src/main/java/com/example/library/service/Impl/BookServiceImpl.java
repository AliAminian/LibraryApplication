package com.example.library.service.Impl;

import com.example.library.model.BookEntity;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public Optional<BookEntity> updateBook(Long id, BookEntity updatedBookEntity) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            BookEntity existingBookEntity = optionalBookEntity.get();
            BeanUtils.copyProperties(updatedBookEntity, existingBookEntity);
            return Optional.of(bookRepository.save(existingBookEntity));
        }
        return Optional.empty(); // Handle the case when the book with the given id is not found
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
