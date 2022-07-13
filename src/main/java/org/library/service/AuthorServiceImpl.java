package org.library.service;

import org.library.dto.AuthorDto;
import org.library.dto.BookDto;
import org.library.model.Author;
import org.library.model.Book;
import org.library.repository.AuthorRepository;
import org.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{


    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookService bookService){
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
    public void saveAll(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> getAllByNames(Collection<String> authorsNames)
    {
        return authorRepository.getAllByNames(authorsNames);
    }

    public static AuthorDto convertAuthorToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setAuthorBooks(author.getAuthorBooks());
        return authorDto;
    }


    public static List<AuthorDto> convertListAuthorToDto(List<Author> authors) {
        return authors.stream()
                .map(AuthorServiceImpl::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    public static Author convertAuthorDtoToAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setAuthorBooks(authorDto.getAuthorBooks());

        return author;
    }

    public static List<Author> convertAuthorDtoListToAuthorList(List<AuthorDto> authorDtoList) {
        return authorDtoList.stream()
                .map(AuthorServiceImpl::convertAuthorDtoToAuthor)
                .collect(Collectors.toList());
    }
}

