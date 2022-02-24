package com.kodilla.libraryrestapi.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TITLES")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TITLE_ID", nullable = false, unique = true)
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="FIRSTNAME")
    private String authorName;

    @Column(name="LASTNAME")
    private String authorLastname;

    @Column(name="YEAR")
    private int pubYear;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Book> titleSet = new HashSet<>();

    public Title(String title, String authorName, String authorLastname, int pubYear) {
        this.title = title;
        this.authorName = authorName;
        this.authorLastname = authorLastname;
        this.pubYear = pubYear;
    }

    public Title(Long id, String title, String authorName, String authorLastname, int pubYear) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.authorLastname = authorLastname;
        this.pubYear = pubYear;
    }


}
