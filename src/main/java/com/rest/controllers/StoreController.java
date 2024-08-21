package com.rest.controllers;

import com.rest.models.store.requests.CreateStoreOrderRequest;
import com.rest.utills.data.Services;
import com.rest.utills.data.Urls;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static com.rest.utills.SpecificationFactory.getDefaultRequestSpecification;

public class StoreController {

    public ValidatableResponse getPetInventory() {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .when()
                .get(Urls.STORE_INVENTORY)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse createStoreOrder(CreateStoreOrderRequest createStoreOrderRequest) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .body(createStoreOrderRequest)
                .when()
                .post(Urls.STORE_ORDER)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse getStoreOrderById(int storeOrderId) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .when()
                .get(String.format(Urls.STORE_ORDER_ID, storeOrderId))
                .then()
                .log()
                .all();
    }

    public ValidatableResponse deleteStoreOrderById(int storeOrderId) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .when()
                .delete(String.format(Urls.STORE_ORDER_ID, storeOrderId))
                .then()
                .log()
                .all();
    }
}
