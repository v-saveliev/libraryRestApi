package org.library.dto;

import org.library.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapperDto {

    private ModelMapper modelMapper;

    @Autowired
    public AuthorMapperDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorPartDto convertAuthorToDto(Author author) {
        return modelMapper.map(author, AuthorPartDto.class);
    }

    public List<AuthorPartDto> convertListAuthorToDto(List<Author> authors) {
        return authors.stream()
                .map(this::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    public Author convertAuthorDtoToEntity(AuthorPartDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }

    public List<Author> convertAuthorDtoListToEntity(List<AuthorPartDto> authorDtoList) {
        return authorDtoList.stream()
                .map(this::convertAuthorDtoToEntity)
                .collect(Collectors.toList());
    }

}
