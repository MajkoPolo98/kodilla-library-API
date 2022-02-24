package com.kodilla.libraryrestapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private Long titleId;
    @JsonProperty("status")
    private String status;
}
