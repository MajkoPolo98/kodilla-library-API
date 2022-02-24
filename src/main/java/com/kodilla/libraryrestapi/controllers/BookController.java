package com.kodilla.libraryrestapi.controllers;

import com.kodilla.libraryrestapi.domain.Book;
import com.kodilla.libraryrestapi.domain.dtos.BookDto;
import com.kodilla.libraryrestapi.exceptions.BookNotFoundException;
import com.kodilla.libraryrestapi.mapper.BookMapper;
import com.kodilla.libraryrestapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @GetMapping(value = "/books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable("id") final Long id) throws BookNotFoundException {
        Book book = bookService.findBook(id).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapToBookDto(book);
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody final BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
    }

    @PutMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(bookService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @DeleteMapping(value = "/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }
}
