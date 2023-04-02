package org.library.dto;

import org.library.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperDto {

    private ModelMapper modelMapper;

    @Autowired
    public BookMapperDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDto convertBookToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book convertBookDtoToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public List<Book> convertBookDtoListToEntity(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(this::convertBookDtoToEntity)
                .collect(Collectors.toList());
    }

    public List<BookDto> convertBookListToDto(List<Book> bookList) {
        return bookList.stream()
                .map(this::convertBookToDto)
                .collect(Collectors.toList());
    }


}
