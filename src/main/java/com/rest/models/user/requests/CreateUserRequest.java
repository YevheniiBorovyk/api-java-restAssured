package com.rest.models.user.requests;

import lombok.Getter;

@Getter
public class CreateUserRequest {

    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer status;

    public static class Builder {

        private final CreateUserRequest createUserRequest;

        public Builder() {
            createUserRequest = new CreateUserRequest();
        }

        public Builder setDefaultValues() {
            createUserRequest.firstName = "firstName";
            createUserRequest.lastName = "lastName";
            createUserRequest.password = "qwerty";
            createUserRequest.phone = "+380933211231";
            createUserRequest.status = 1000;
            return this;
        }

        public Builder id(int id){
            createUserRequest.id = id;
            return this;
        }

        public Builder userName(String userName){
            createUserRequest.userName = userName;
            return this;
        }

        public Builder email(String email){
            createUserRequest.email = email;
            return this;
        }

        public CreateUserRequest build() {
            return createUserRequest;
        }
    }
}
