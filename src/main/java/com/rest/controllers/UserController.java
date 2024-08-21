package com.rest.controllers;

import com.rest.models.user.requests.CreateUserRequest;
import com.rest.utills.data.Services;
import com.rest.utills.data.Urls;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static com.rest.utills.SpecificationFactory.getDefaultRequestSpecification;

public class UserController {

    public ValidatableResponse createUser(CreateUserRequest createUserRequest) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .body(createUserRequest)
                .when()
                .post(Urls.USER_URL)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse createListOfUsers(CreateUserRequest... createUserRequest) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .body(createUserRequest)
                .when()
                .post(Urls.USER_CREATE_WITH_ARRAY)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse logIn(String userName, String userPassword) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .param("username", userName)
                .param("password", userPassword)
                .when()
                .get(Urls.LOGIN_URL)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse logOut() {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .when()
                .get(Urls.LOGOUT_URL)
                .then()
                .log()
                .all();
    }
}
