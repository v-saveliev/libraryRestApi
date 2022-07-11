package org.library.service;

import org.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author getById(Long id);

    Author getByName(Author author);

    void save(Author author);

    void delete(Author author);

    List<Author> getAll();

}
