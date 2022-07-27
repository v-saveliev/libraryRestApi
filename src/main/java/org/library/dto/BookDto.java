package org.library.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

       private Long id;
       private String title;
       private Integer pages;
       private UserPartDto user;
       private List<AuthorPartDto> bookAuthors;

}
