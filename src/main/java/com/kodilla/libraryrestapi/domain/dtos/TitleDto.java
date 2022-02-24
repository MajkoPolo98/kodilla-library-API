package com.kodilla.libraryrestapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("authorName")
    private String authorName;
    @JsonProperty("authorLastname")
    private String authorLastname;
    @JsonProperty("pubYear")
    private int pubYear;
}
