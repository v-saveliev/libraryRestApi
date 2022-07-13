package org.library.service;

import org.library.dto.AuthorDto;
import org.library.model.Author;
import org.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{


    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    };

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author getByName(Author author) {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Author> example = Example.of(author, customExampleMatcher);

        return authorRepository.findOne(example).orElse(null);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public List<AuthorDto> getAll() {
        return convertListAuthorToDto(authorRepository.findAll());
    }

    public AuthorDto convertAuthorToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        return authorDto;
    }


    public List<AuthorDto> convertListAuthorToDto(List<Author> authors) {
        return authors.stream()
                .map(this::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    public Author convertAuthorDtoToAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());

        return author;
    }

    public List<Author> convertAuthorDtoListToAuthorList(List<AuthorDto> authorDtoList) {
        return authorDtoList.stream()
                .map(this::convertAuthorDtoToAuthor)
                .collect(Collectors.toList());
    }
}

