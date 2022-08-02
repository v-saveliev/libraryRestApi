package org.library.controllers;

import lombok.RequiredArgsConstructor;
import org.library.dto.AuthorDto;
import org.library.dto.AuthorMapperDto;
import org.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authors/")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;
    private final AuthorMapperDto authorMapper;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long authorId) {
        if(authorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AuthorDto authorDto = authorMapper.convertAuthorToDto(authorService.getById(authorId));

        if(authorDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }
}
