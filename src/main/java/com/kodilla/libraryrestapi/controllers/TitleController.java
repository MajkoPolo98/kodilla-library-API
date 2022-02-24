package com.kodilla.libraryrestapi.controllers;

import com.kodilla.libraryrestapi.domain.Title;
import com.kodilla.libraryrestapi.domain.dtos.TitleDto;
import com.kodilla.libraryrestapi.exceptions.TitleNotFoundException;
import com.kodilla.libraryrestapi.mapper.TitleMapper;
import com.kodilla.libraryrestapi.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class TitleController {

    private final TitleMapper titleMapper;
    private final TitleService titleService;

    @GetMapping("/title/{id}")
    public long getAvailableTitleQty(@PathVariable("id") final Long id) {
        return titleService.getAvailableCount(id);
    }

    @PostMapping(value = "/titles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody final TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        titleService.saveTitle(title);
    }

    @GetMapping("/titles/{id}")
    public TitleDto getTitle(@PathVariable("id") final Long id) throws TitleNotFoundException {
        Title title = titleService.findTitle(id).orElseThrow(TitleNotFoundException::new);
        return titleMapper.mapToTitleDto(title);
    }

    @PutMapping(value = "/titles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto updateTask(@RequestBody TitleDto titleDto) {
        return titleMapper.mapToTitleDto(titleService.saveTitle(titleMapper.mapToTitle(titleDto)));
    }

    @DeleteMapping(value = "/titles/{titleId}")
    public void deleteTask(@PathVariable Long titleId) {
        titleService.deleteTitleById(titleId);
    }

    @GetMapping(value = "/titles")
    public List<TitleDto> getTitles() {
        return titleMapper.mapToTitleDtoList(titleService.getAllTitles());
    }
}
