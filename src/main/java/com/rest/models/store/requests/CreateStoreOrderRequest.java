package com.rest.models.store.requests;

import lombok.Getter;

import static com.rest.utills.data.TestData.getTimeStamp;

@Getter
public class CreateStoreOrderRequest {

    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    public static class Builder {

        private final CreateStoreOrderRequest createUserRequest;

        public Builder() {
            createUserRequest = new CreateStoreOrderRequest();
        }

        public Builder setDefaultValues() {
            createUserRequest.shipDate = getTimeStamp().toString();
            createUserRequest.status = "placed";
            createUserRequest.complete = true;
            return this;
        }

        public Builder id(Integer id) {
            createUserRequest.id = id;
            return this;
        }

        public Builder petId(Integer petId) {
            createUserRequest.petId = petId;
            return this;
        }

        public Builder quantity(Integer quantity) {
            createUserRequest.quantity = quantity;
            return this;
        }

        public CreateStoreOrderRequest build() {
            return createUserRequest;
        }
    }
}
