package com.rest.models.store.responses;

import lombok.Getter;

@Getter
public class ErrorBodyResponse {

    private Integer code;
    private String type;
    private String message;
}
