package org.library.controllers;

import org.library.dto.BookDto;
import org.library.dto.Converter;
import org.library.model.Author;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.AuthorService;
import org.library.service.BookService;
import org.library.service.BookServiceImpl;
import org.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books/")
public class BookRestController {

    private BookService bookService;
    private AuthorService authorService;
    private UserService userService;

    @Autowired
    public BookRestController(BookService bookService, AuthorService authorService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.userService = userService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long bookId) {
        if(bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BookDto bookDto = Converter.convertBookToDto(bookService.getById(bookId));

        if(bookDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        if (bookDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book book = Converter.convertBookDtoToEntity(bookDto);

        Map<String, Author> authorsMap = authorService.getAll().stream()
                .collect(Collectors.toMap(Author::getName, Function.identity(), (a1, a2) -> a1));
        Set<String> authorsNames = authorsMap.keySet();
        List<Author> bookAuthors = book.getBookAuthors();
        List<Author> authorsToSave = new ArrayList<>();

        Author currentAuthor;
        String currentAuthorName;
        for (int i = bookAuthors.size() - 1; i >= 0 ; i--) {
            currentAuthor = bookAuthors.get(i);
            currentAuthorName = currentAuthor.getName();

            if (authorsNames.contains(currentAuthorName)) {
                bookAuthors.set(i, authorsMap.get(currentAuthorName));
            } else {
                currentAuthor.addBookToList(book);
                authorsToSave.add(currentAuthor);
                bookAuthors.remove(i);
            }
        }

        authorService.saveAll(authorsToSave);
        List<Author> savedAuthors = authorService.getAllByNames(authorsToSave.stream()
                .map(Author::getName)
                .collect(Collectors.toList()));
        bookAuthors.addAll(savedAuthors);

        book.setUser(userService.getById((long) 1));

        this.bookService.save(book);
        return new ResponseEntity<>(Converter.convertBookToDto(book), HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody Book book, UriComponentsBuilder builder) {
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.bookService.save(book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "id", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        Book book = bookService.getById(id);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.bookService.delete(book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAll();

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }


}
