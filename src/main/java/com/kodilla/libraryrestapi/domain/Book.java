package com.kodilla.libraryrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQuery(
        name = "Book.getAvailableTitles",
        query = "SELECT COUNT(*) FROM BOOKS WHERE title_id = :TITLE_ID AND status = 'available'"
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @Column(name = "STATUS")
    private String status;

    public Book(Title title) {
        this.title = title;
    }
}
