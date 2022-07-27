package org.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private JsonNode info;
    private List<BookPartDto> authorBooks;
}
