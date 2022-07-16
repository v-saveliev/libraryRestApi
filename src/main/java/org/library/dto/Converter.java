package org.library.dto;

import org.library.model.Author;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.AuthorServiceImpl;
import org.library.service.BookServiceImpl;
import org.library.service.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    // BookDtoConverters
    public static BookDto convertBookToDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPages(book.getPages());
        bookDto.setOwner(book.getUser().getUsername());
        bookDto.setBookAuthors(Converter.convertListAuthorToDto(book.getBookAuthors()));

        return bookDto;
    }

    public static Book convertBookDtoToEntity(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setPages(bookDto.getPages());

        UserDto userDto = bookDto.getUser();
        if (userDto != null) book.setUser(Converter.convertUserDtoToEntity(userDto));
        List<AuthorDto> authorDtoList = bookDto.getBookAuthors();
        if (authorDtoList != null)
            book.setBookAuthors(Converter.convertAuthorDtoListToEntity(authorDtoList));

        return book;
    }

    public static List<Book> convertBookDtoListToEntity(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(Converter::convertBookDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<BookDto> convertBookListToDto(List<Book> bookList) {
        return bookList.stream()
                .map(Converter::convertBookToDto)
                .collect(Collectors.toList());
    }

    // AuthorDtoConverters
    public static AuthorDto convertAuthorToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setAuthorBooks(author.getAuthorBooks());
        return authorDto;
    }

    public static List<AuthorDto> convertListAuthorToDto(List<Author> authors) {
        return authors.stream()
                .map(Converter::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    public static Author convertAuthorDtoToEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setAuthorBooks(authorDto.getAuthorBooks());

        return author;
    }

    public static List<Author> convertAuthorDtoListToEntity(List<AuthorDto> authorDtoList) {
        return authorDtoList.stream()
                .map(Converter::convertAuthorDtoToEntity)
                .collect(Collectors.toList());
    }

    // UserDtoConverters
    public static UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public static List<UserDto> convertListUserToDto(List<User> users) {
        return users.stream()
                .map(Converter::convertUserToDto)
                .collect(Collectors.toList());
    }

    public static User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        return user;
    }

    public static List<User> convertUserDtoListToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(Converter::convertUserDtoToEntity)
                .collect(Collectors.toList());
    }
}
