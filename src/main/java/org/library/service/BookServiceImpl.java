package org.library.service;

import lombok.extern.slf4j.Slf4j;
import org.library.dto.AuthorDto;
import org.library.dto.BookDto;
import org.library.model.Author;
import org.library.model.Book;
import org.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    AuthorService authorService;
    UserService userService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, UserService userService){
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.userService = userService;
    };

    @Override
    public Book getById(Long id) {
        log.info("BookService getById {}", id);
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Book book) {
        log.info("BookService save {}", book);
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        log.info("BookService delete {}", book);
        bookRepository.delete(book);
    }

    @Override
    public List<BookDto> getAll() {
        log.info("BookService getAll");
        return bookRepository.findAll().stream()
                .map(this::convertBookToDto)
                .collect(Collectors.toList());
    }

    public BookDto convertBookToDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPages(book.getPages());
        bookDto.setOwner(book.getUser().getUsername());
        bookDto.setBookAuthors(authorService.convertListAuthorToDto(book.getBookAuthors()));

        return bookDto;
    }

    public Book convertBookDtoToBook(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setPages(bookDto.getPages());
        book.setUser(userService.convertUserDtoToUser(bookDto.getUser()));
        book.setBookAuthors(authorService.convertAuthorDtoListToAuthorList(bookDto.getBookAuthors()));

        return book;
    }

}
