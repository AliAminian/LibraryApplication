package com.example.library.controller;

import com.example.library.controller.aop.RateLimited;
import com.example.library.dto.AuthResponseDTO;
import com.example.library.dto.BookDTO;
import com.example.library.model.BookEntity;
import com.example.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Api(tags = "Book Management")
public class BookController {

    private final BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping @RateLimited
    @ApiOperation(value = "get all registered books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the book with the id is found", response = List.class)
    })
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookEntity> bookEntities = bookService.getAllBooks();
        List<BookDTO> bookList =  bookEntities.stream()
                                            .map(this::convertEntityToDto)
                                            .toList();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get book with the id", produces = "number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the book with the id is found", response = BookDTO.class),
            @ApiResponse(code = 404, message = "the book with the id not found"),
    })
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        Optional<BookEntity> bookEntity = bookService.getBookById(id);
        return bookEntity.map(
                entity -> ResponseEntity.ok(convertEntityToDto(entity))
        ).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    @ApiOperation(value = "create book with new data", produces = "BookDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the book with new information registered", response = BookDTO.class),
    })
    public ResponseEntity<BookDTO> createBook(@RequestBody @Validated BookDTO bookDto) {
        BookEntity bookEntity = convertDtoToEntity(bookDto);
        BookEntity createdBookEntity = bookService.createBook(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertEntityToDto(createdBookEntity));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update the information of the book with an id", produces = "number, BookDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the book with new information and the id updated", response = BookDTO.class),
            @ApiResponse(code = 404, message = "the book with the information is not registered")
    })
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDto) {
        BookEntity updatedBookEntity = convertDtoToEntity(bookDto);
        Optional<BookEntity> result = bookService.updateBook(id, updatedBookEntity);
        return result.map(
                bookEntity -> ResponseEntity.ok(convertEntityToDto(bookEntity))
        ).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a book by the id", produces = "number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the book deleted")
    })
    public ResponseEntity<Objects> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    // Helper method to convert Entity to DTO
    private BookDTO convertEntityToDto(BookEntity bookEntity) {
        BookDTO bookDto = new BookDTO();
        BeanUtils.copyProperties(bookEntity, bookDto);
        return bookDto;
    }

    // Helper method to convert DTO to Entity
    private BookEntity convertDtoToEntity(BookDTO bookDto) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookDto, bookEntity);
        return bookEntity;
    }
}
