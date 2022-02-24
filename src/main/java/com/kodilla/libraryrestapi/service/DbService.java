package com.kodilla.libraryrestapi.service;

import com.kodilla.libraryrestapi.domain.*;
import com.kodilla.libraryrestapi.exceptions.BookNotFoundException;
import com.kodilla.libraryrestapi.exceptions.RentalNotFoundException;
import com.kodilla.libraryrestapi.exceptions.UserNotFoundException;
import com.kodilla.libraryrestapi.repository.BookRepository;
import com.kodilla.libraryrestapi.repository.RentalRepository;
import com.kodilla.libraryrestapi.repository.TitleRepository;
import com.kodilla.libraryrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class DbService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private UserRepository userRepository;

    //Book
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


    //Rental
    public Optional<Rental> findRental(Long rentId){
        return rentalRepository.findById(rentId);
    }

    public Rental rentBook(Rental rental) throws BookNotFoundException, UserNotFoundException {
        Long userId = rental.getUser().getId();
        Long bookId = rental.getBook().getId();
        Book book = bookRepository.findByIdAndStatus(bookId, "available").orElseThrow(BookNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        book.setStatus("rent");
        bookRepository.save(book);
        rental.setBook(book);
        rental.setRentDate(LocalDate.now());
        rental.setUser(user);
        rentalRepository.save(rental);
        return rental;
    }

    public Rental returnBook(Long rentId) throws RentalNotFoundException{
        Rental rental = rentalRepository.findById(rentId).orElseThrow(RentalNotFoundException::new);
        rental.getBook().setStatus("available");
        rental.setReturnDate(LocalDate.now());
        bookRepository.save(rental.getBook());
        rentalRepository.save(rental);
        return rental;
    }

    public List<Rental> getAllRentals(){
        return rentalRepository.findAll();
    }


    //Title
    public Optional<Title> findTitle(Long titleId){
        return titleRepository.findById(titleId);
    }

    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitleById(Long titleId){
        titleRepository.deleteById(titleId);
    }

    public List<Title> getAllTitles(){
        return titleRepository.findAll();
    }

    public long getAvailableCount(Long titleId){
        return bookRepository.getAvailableTitles(titleId);
    }


    //User
    public Optional<User> findUser(Long userId){
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
