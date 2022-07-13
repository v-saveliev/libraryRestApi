package org.library.service;

import org.library.dto.BookDto;
import org.library.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public interface BookService {

    Book getById(Long id);

    void save(Book book);

    void delete(Book book);

    List<BookDto> getAll();

}
