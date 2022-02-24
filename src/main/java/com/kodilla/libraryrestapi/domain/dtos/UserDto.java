package com.kodilla.libraryrestapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UserDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userName")
    private String authorName;
    @JsonProperty("userLastname")
    private String authorLastname;
    @JsonProperty("signUpDate")
    private LocalDate signUpDate;
}
