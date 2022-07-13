package org.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AuthorDto {
    @JsonIgnore
    private Long id;
    private String name;
}
