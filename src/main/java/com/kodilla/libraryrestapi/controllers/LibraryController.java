package com.kodilla.libraryrestapi.controllers;

import com.kodilla.libraryrestapi.domain.*;
import com.kodilla.libraryrestapi.domain.dtos.BookDto;
import com.kodilla.libraryrestapi.domain.dtos.RentalDto;
import com.kodilla.libraryrestapi.domain.dtos.TitleDto;
import com.kodilla.libraryrestapi.domain.dtos.UserDto;
import com.kodilla.libraryrestapi.exceptions.BookNotFoundException;
import com.kodilla.libraryrestapi.exceptions.RentalNotFoundException;
import com.kodilla.libraryrestapi.exceptions.UserNotFoundException;
import com.kodilla.libraryrestapi.mapper.BookMapper;
import com.kodilla.libraryrestapi.mapper.RentalMapper;
import com.kodilla.libraryrestapi.mapper.TitleMapper;
import com.kodilla.libraryrestapi.mapper.UserMapper;
import com.kodilla.libraryrestapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final BookMapper bookMapper;
    private final TitleMapper titleMapper;
    private final RentalMapper rentalMapper;
    private final UserMapper userMapper;

    private final DbService service;

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") final Long id) throws BookNotFoundException {
        Book book = service.findBook(id).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapToBookDto(book);
    }

    @GetMapping("/title/{id}")
    public long getAvailableTitle(@PathVariable("id") final Long id) throws BookNotFoundException {
        return service.getAvailableCount(id);
    }

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
    }

    @PostMapping(value = "/addTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody final TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        service.saveTitle(title);
    }

    @PostMapping(value = "/addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody final BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        service.saveBook(book);
    }

    @PostMapping(value = "/rent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto rentBook(@RequestBody final RentalDto rentalDto) throws BookNotFoundException, UserNotFoundException {
        Rental rental = service.rentBook(rentalMapper.mapToRental(rentalDto));
        return rentalMapper.mapToRentalDto(rental);
    }

    @PutMapping(value = "/return/{id}")
    public RentalDto returnBook(@PathVariable("id") final Long id) throws RentalNotFoundException {
        Rental rental = service.findRental(id).orElseThrow(RentalNotFoundException::new);
        service.returnBook(id);
        return rentalMapper.mapToRentalDto(rental);
    }
}

