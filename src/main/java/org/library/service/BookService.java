package org.library.service;

import org.library.model.Book;

import java.util.List;

public interface BookService {

    Book getById(Long id);

    void save(Book book);

    void delete(Book book);

    List<Book> getAll();
}
