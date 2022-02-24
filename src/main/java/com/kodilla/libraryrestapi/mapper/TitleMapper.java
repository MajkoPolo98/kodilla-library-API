package com.kodilla.libraryrestapi.mapper;

import com.kodilla.libraryrestapi.domain.Title;
import com.kodilla.libraryrestapi.domain.dtos.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleMapper {
    public Title mapToTitle(TitleDto titleDto){
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthorName(),
                titleDto.getAuthorLastname(),
                titleDto.getPubYear()
        );
    }

    public TitleDto mapToTitleDto(Title title){
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthorName(),
                title.getAuthorLastname(),
                title.getPubYear()
        );
    }
    public List<TitleDto> mapToTitleDtoList(List<Title> titles){
        return titles.stream().map(this::mapToTitleDto).collect(Collectors.toList());
    }
}

