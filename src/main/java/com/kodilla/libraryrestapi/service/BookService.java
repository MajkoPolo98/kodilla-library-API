package com.kodilla.libraryrestapi.service;

import com.kodilla.libraryrestapi.domain.Book;
import com.kodilla.libraryrestapi.repository.BookRepository;
import com.kodilla.libraryrestapi.repository.RentalRepository;
import com.kodilla.libraryrestapi.repository.TitleRepository;
import com.kodilla.libraryrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findBook(Long bookId){
        return bookRepository.findById(bookId);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

}
