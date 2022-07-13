package org.library.service;

import org.library.dto.AuthorDto;
import org.library.model.Author;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AuthorService {

    Author getById(Long id);

    Author getByName(Author author);

    void save(Author author);

    void saveAll(List<Author> authors);

    void delete(Author author);

    List<Author> getAll();

    AuthorDto convertAuthorToDto(Author author);

    List<AuthorDto> convertListAuthorToDto(List<Author> authors);

    Author convertAuthorDtoToAuthor(AuthorDto authorDto);

    List<Author> convertAuthorDtoListToAuthorList(List<AuthorDto> authorDtoList);

    List<Author> getAllByExample(Collection<String> authorsNames);

}
