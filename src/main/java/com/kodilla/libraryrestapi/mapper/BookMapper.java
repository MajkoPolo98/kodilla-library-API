package com.kodilla.libraryrestapi.mapper;

import com.kodilla.libraryrestapi.domain.Book;
import com.kodilla.libraryrestapi.domain.dtos.BookDto;
import com.kodilla.libraryrestapi.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {
    @Autowired
    TitleRepository titleRepository;

    public Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                titleRepository.findById(bookDto.getTitleId()).orElseThrow(),
                bookDto.getStatus()
        );
    }

    public BookDto mapToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle().getId(),
                book.getStatus()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> books){
        return books.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }
}

