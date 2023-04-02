package org.library.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AuthorDto {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private JsonNode info;
    private List<BookPartDto> authorBooks;
}
