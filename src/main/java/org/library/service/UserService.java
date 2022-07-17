package org.library.service;

import org.library.dto.UserDto;
import org.library.model.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {

    User register(User user);

    User getById(Long id);

    User findByUsername(String username);

    void delete(User user);

    List<User> getAll();

}
