package com.kodilla.libraryrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RENTAL")
public class Rental {

    @Id
    @GeneratedValue
    @Column(name="RENT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;

    @Column(name="RENT_DATE")
    private LocalDate rentDate;

    @Column(name="RETURN_DATE")
    private LocalDate returnDate;

    public Rental(User user, Book book, LocalDate rentDate, LocalDate returnDate) {
        this.user = user;
        this.book = book;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
