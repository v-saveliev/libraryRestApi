package org.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {

    @JsonIgnore
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
}
