package com.kodilla.libraryrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String authorName;

    @Column(name = "LASTNAME")
    private String authorLastname;

    @Column(name = "SIGN_UP_DATE")
    private LocalDate signUpDate;

    public User(String authorName, String authorLastname) {
        this.authorName = authorName;
        this.authorLastname = authorLastname;
        signUpDate = LocalDate.now();
    }
}
