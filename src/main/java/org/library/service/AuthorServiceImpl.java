package org.library.service;

import org.library.model.Author;
import org.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{


    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author getByName(String authorName) {
        Author author = new Author();
        author.setName(authorName);
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
    public List<Author> getAll(int page, int limit) {
        if (limit == 0) limit = 10;
        return authorRepository.findAll(PageRequest.of(page, limit)).toList();
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll(PageRequest.of(0, 100)).toList();
    }

    @Override
    public List<Author> getAllByNames(int limit, Collection<String> authorsNames)
    {
        return authorRepository.getAllByNames(PageRequest.of(0, limit), authorsNames);
    }


}

