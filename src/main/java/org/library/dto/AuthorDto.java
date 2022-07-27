package org.library.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private String info;
    private List<BookPartDto> authorBooks;
}
