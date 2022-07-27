package org.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AuthorPartDto {
    @JsonIgnore
    private Long id;
    private String name;
}
