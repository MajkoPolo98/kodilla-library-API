package com.kodilla.libraryrestapi.repository;

import com.kodilla.libraryrestapi.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    Optional<Book> findByIdAndStatus(Long bookId,String status);

    @Query
    long getAvailableTitles(@Param("TITLE_ID") Long titleId);
}
