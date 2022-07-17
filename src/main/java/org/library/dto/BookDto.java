package org.library.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

       private Long id;
       private String title;
       private Integer pages;
       @JsonIgnore
       private UserDto user;
       private String owner;
       private List<AuthorDto> bookAuthors;

}
