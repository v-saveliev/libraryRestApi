package org.library.service;

import lombok.extern.slf4j.Slf4j;
import org.library.dto.AuthorDto;
import org.library.dto.UserDto;
import org.library.model.Author;
import org.library.model.Book;
import org.library.model.User;
import org.library.repository.BookRepository;
import org.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    };

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<UserDto> getAll() {
        return convertListUserToDto(userRepository.findAll());
    }

    public UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }


    public List<UserDto> convertListUserToDto(List<User> users) {
        return users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        return user;
    }

    public List<User> convertUserDtoListToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::convertUserDtoToUser)
                .collect(Collectors.toList());
    }
}
