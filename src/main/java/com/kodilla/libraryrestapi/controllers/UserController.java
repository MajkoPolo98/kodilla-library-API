package com.kodilla.libraryrestapi.controllers;

import com.kodilla.libraryrestapi.domain.User;
import com.kodilla.libraryrestapi.domain.dtos.UserDto;
import com.kodilla.libraryrestapi.exceptions.UserNotFoundException;
import com.kodilla.libraryrestapi.mapper.UserMapper;
import com.kodilla.libraryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable("id") final Long id) throws UserNotFoundException {
        User user = userService.findUser(id).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateTask(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "/users/{userId}")
    public void deleteTask(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping(value = "/users")
    public List<UserDto> getBooks() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }
}
