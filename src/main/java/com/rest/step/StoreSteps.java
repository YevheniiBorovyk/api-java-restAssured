package com.rest.step;

import com.rest.controllers.StoreController;
import com.rest.models.store.requests.CreateStoreOrderRequest;
import com.rest.models.store.responses.CreateStoreOrderResponse;
import com.rest.models.store.responses.ErrorBodyResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

public class StoreSteps {

    private final StoreController storeController = new StoreController();

    @Step("Get pet inventory")
    public JsonPath getPetInventory(Integer statusCode) {
        return storeController.getPetInventory()
                .statusCode(statusCode)
                .extract()
                .jsonPath();
    }

    @Step("Create store order")
    public CreateStoreOrderResponse createStoreOrder(int statusCode,
            CreateStoreOrderRequest createStoreOrderRequest) {
        return storeController.createStoreOrder(createStoreOrderRequest)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(CreateStoreOrderResponse.class);
    }

    //Why generic? I'm just showing off:)
    @Step("Get store order by id")
    public <T> T getStoreOrderById(Integer statusCode, int id, Class<T> expectedClass) {
        return storeController.getStoreOrderById(id)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(expectedClass);
    }

    @Step("Delete store order by id")
    public void deleteStoreOrderById(Integer statusCode, int id) {
        storeController.deleteStoreOrderById(id)
                .statusCode(statusCode);
    }

    @Step("Delete store order by id with error")
    public ErrorBodyResponse deleteStoreOrderByIdWithError(Integer statusCode, int id) {
        return storeController.deleteStoreOrderById(id)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ErrorBodyResponse.class);
    }
}
