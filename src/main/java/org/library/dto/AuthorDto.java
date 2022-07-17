package org.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.library.model.Book;

import java.util.List;

@Data
public class AuthorDto {
    @JsonIgnore
    private Long id;
    private String name;
    @JsonIgnore
    private List<Book> authorBooks;
}
