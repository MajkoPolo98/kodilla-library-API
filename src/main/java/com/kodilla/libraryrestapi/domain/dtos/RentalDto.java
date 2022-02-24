package com.kodilla.libraryrestapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("user")
    private Long userId;
    @JsonProperty("book")
    private Long bookId;
    @JsonProperty("rentDate")
    private LocalDate rentDate;
    @JsonProperty("returnDate")
    private LocalDate returnDate;
}
