package org.library.service;

import lombok.extern.slf4j.Slf4j;
import org.library.model.Book;
import org.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    };

    @Override
    public Book getById(Long id) {
//        log.info("BookService getById {}", id);
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Book book) {
//        log.info("BookService save {}", book);
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
//        log.info("BookService delete {}", book);
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
//        log.info("BookService getAll");
        return bookRepository.findAll();
    }
}
