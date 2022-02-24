package com.kodilla.libraryrestapi.controllers;

import com.kodilla.libraryrestapi.domain.Rental;
import com.kodilla.libraryrestapi.domain.dtos.RentalDto;
import com.kodilla.libraryrestapi.exceptions.BookNotFoundException;
import com.kodilla.libraryrestapi.exceptions.RentalNotFoundException;
import com.kodilla.libraryrestapi.exceptions.UserNotFoundException;
import com.kodilla.libraryrestapi.mapper.RentalMapper;
import com.kodilla.libraryrestapi.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class RentalController {

    private final RentalMapper rentalMapper;
    private final RentalService rentalService;

    @PostMapping(value = "/rent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto rentBook(@RequestBody final RentalDto rentalDto) throws BookNotFoundException, UserNotFoundException {
        Rental rental = rentalService.rentBook(rentalMapper.mapToRental(rentalDto));
        return rentalMapper.mapToRentalDto(rental);
    }

    @PutMapping(value = "/rent/{id}")
    public RentalDto returnBook(@PathVariable("id") final Long id) throws RentalNotFoundException {
        Rental rental = rentalService.findRental(id).orElseThrow(RentalNotFoundException::new);
        rentalService.returnBook(id);
        return rentalMapper.mapToRentalDto(rental);
    }

    @GetMapping("/rentals/{id}")
    public RentalDto getRental(@PathVariable("id") final Long id) throws RentalNotFoundException {
        Rental rental = rentalService.findRental(id).orElseThrow(RentalNotFoundException::new);
        return rentalMapper.mapToRentalDto(rental);
    }

    @PutMapping(value = "/rentals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto updateRental(@RequestBody RentalDto rentalDto) {
        return rentalMapper.mapToRentalDto(rentalService.saveRental(rentalMapper.mapToRental(rentalDto)));
    }

    @DeleteMapping(value = "/rentals/{rentalId}")
    public void deleteRental(@PathVariable Long rentalId) {
        rentalService.deleteRentalById(rentalId);
    }

    @GetMapping(value = "/rentals")
    public List<RentalDto> getRentals() {
        return rentalMapper.mapToRentalDtoList(rentalService.getAllRentals());
    }
}
