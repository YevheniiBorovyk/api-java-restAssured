package com.rest.models.pet.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetResponse {

    private Integer code;
    private String type;
    private String message;
}
