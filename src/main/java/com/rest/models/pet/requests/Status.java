package com.rest.models.pet.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String value;
}
