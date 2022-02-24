package com.kodilla.libraryrestapi.mapper;

import com.kodilla.libraryrestapi.domain.User;
import com.kodilla.libraryrestapi.domain.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getAuthorName(),
                userDto.getAuthorLastname(),
                userDto.getSignUpDate()
        );
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getAuthorName(),
                user.getAuthorLastname(),
                user.getSignUpDate()
        );
    }

    public List<UserDto> mapToUserDtoList(List<User> users){
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}

