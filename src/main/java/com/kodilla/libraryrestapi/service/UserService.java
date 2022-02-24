package com.kodilla.libraryrestapi.service;

import com.kodilla.libraryrestapi.domain.User;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUser(Long userId){
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
