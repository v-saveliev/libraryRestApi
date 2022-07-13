package org.library.service;

import org.library.dto.AuthorDto;
import org.library.model.Author;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface AuthorService {

    Author getById(Long id);

    Author getByName(Author author);

    void save(Author author);

    void delete(Author author);

    List<AuthorDto> getAll();

    AuthorDto convertAuthorToDto(Author author);

    List<AuthorDto> convertListAuthorToDto(List<Author> authors);

    Author convertAuthorDtoToAuthor(AuthorDto authorDto);

    List<Author> convertAuthorDtoListToAuthorList(List<AuthorDto> authorDtoList);
}
