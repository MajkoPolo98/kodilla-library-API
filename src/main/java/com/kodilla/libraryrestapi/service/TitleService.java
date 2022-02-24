package com.kodilla.libraryrestapi.service;

import com.kodilla.libraryrestapi.domain.Title;
import com.kodilla.libraryrestapi.repository.BookRepository;
import com.kodilla.libraryrestapi.repository.RentalRepository;
import com.kodilla.libraryrestapi.repository.TitleRepository;
import com.kodilla.libraryrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class TitleService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TitleRepository titleRepository;

    public Optional<Title> findTitle(Long titleId){
        return titleRepository.findById(titleId);
    }

    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitleById(Long titleId){
        titleRepository.deleteById(titleId);
    }

    public List<Title> getAllTitles(){
        return titleRepository.findAll();
    }

    public long getAvailableCount(Long titleId) {
        return bookRepository.getAvailableTitles(titleId);
    }
}
