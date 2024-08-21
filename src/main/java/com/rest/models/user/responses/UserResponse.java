package com.rest.models.user.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private Integer code;
    private String message;
}
