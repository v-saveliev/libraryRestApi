package org.library.service;

import org.library.model.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    void save(User user);

    void delete(User user);

    List<User> getAll();

}
