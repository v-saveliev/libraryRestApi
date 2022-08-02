package org.library.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class BookDto {

       private Long id;
       @NotBlank(message = "Title is mandatory")
       private String title;
       @Min(value = 1, message = "page can't be less than 1")
       private Integer pages;
       private UserPartDto user;
       private List<AuthorPartDto> bookAuthors;

}
