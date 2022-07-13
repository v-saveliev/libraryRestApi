package org.library.service;

import org.library.dto.UserDto;
import org.library.model.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {

    User getById(Long id);

    void save(User user);

    void delete(User user);

    List<UserDto> getAll();

}
