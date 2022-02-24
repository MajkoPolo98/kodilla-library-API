package com.kodilla.libraryrestapi.service;

import com.kodilla.libraryrestapi.domain.Book;
import com.kodilla.libraryrestapi.domain.Rental;
import com.kodilla.libraryrestapi.domain.User;
import com.kodilla.libraryrestapi.exceptions.BookNotFoundException;
import com.kodilla.libraryrestapi.exceptions.RentalNotFoundException;
import com.kodilla.libraryrestapi.exceptions.UserNotFoundException;
import com.kodilla.libraryrestapi.repository.BookRepository;
import com.kodilla.libraryrestapi.repository.RentalRepository;
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
public class RentalService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserRepository userRepository;

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

    public Rental returnBook(Long rentId) throws RentalNotFoundException {
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

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRentalById(Long rentalId){
        rentalRepository.deleteById(rentalId);
    }
}
