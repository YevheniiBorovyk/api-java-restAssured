package com.rest.step;

import com.rest.controllers.UserController;
import com.rest.models.user.requests.CreateUserRequest;
import com.rest.models.user.responses.UserResponse;
import io.qameta.allure.Step;

public class UserSteps {

    private final UserController userController = new UserController();

    @Step("Create user")
    public UserResponse createUser(int statusCode, CreateUserRequest createUserRequest) {
        return userController.createUser(createUserRequest)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(UserResponse.class);
    }

    @Step("Create list of users")
    public UserResponse createListOfUsers(int statusCode, CreateUserRequest... createUserRequest) {
        return userController.createListOfUsers(createUserRequest)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(UserResponse.class);
    }

    @Step("Log In")
    public UserResponse logIn(int statusCode, String userName, String userPassword) {
        return userController.logIn(userName, userPassword)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(UserResponse.class);
    }

    @Step("Log Out")
    public UserResponse logOut(int statusCode) {
        return userController.logOut()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(UserResponse.class);
    }
}
