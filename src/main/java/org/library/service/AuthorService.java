package org.library.service;

import org.library.model.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorService {

    Author getById(Long id);

    Author getByName(String authorName);

    void save(Author author);

    void saveAll(List<Author> authors);

    void delete(Author author);

    List<Author> getAll(int page, int limit);

    List<Author> getAll();

    List<Author> getAllByNames(int limit, Collection<String> authorsNames);

}
