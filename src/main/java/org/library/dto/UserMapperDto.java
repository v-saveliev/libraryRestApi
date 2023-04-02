package org.library.dto;

import org.library.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperDto {

    private ModelMapper modelMapper;

    @Autowired
    public UserMapperDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertListUserToDto(List<User> users) {
        return users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    public User convertUserDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public List<User> convertUserDtoListToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::convertUserDtoToEntity)
                .collect(Collectors.toList());
    }
}
