package com.rest.step;

import com.rest.controllers.PetController;
import com.rest.models.pet.requests.PetToStoreRequest;
import com.rest.models.pet.requests.Status;
import com.rest.models.pet.responses.PetToStoreResponse;
import com.rest.models.pet.responses.PetResponse;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.List;

public class PetSteps {

    private final PetController petController = new PetController();

    @Step("upload pet image")
    public PetResponse uploadPetImage(int statusCode, String petId, String additionalMetadata) {
        return petController.UploadPetImage(petId, additionalMetadata)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(PetResponse.class);
    }

    @Step("add pets to store")
    public PetToStoreResponse addPetToStore(int statusCode, PetToStoreRequest petToStoreRequest) {
        return petController.addPetToStore(petToStoreRequest)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(PetToStoreResponse.class);
    }

    @Step("Update existing pet")
    public PetToStoreResponse updateExistingPet(int statusCode, PetToStoreRequest petToStoreRequest) {
        return petController.updateExitingPet(petToStoreRequest)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(PetToStoreResponse.class);
    }

    @Step("Find pet by statuses")
    public PetToStoreResponse[] findPetByStatus(int statusCode, Status... statuses) {
        return petController.findPetByStatus(List.of(statuses))
                .statusCode(statusCode)
                .extract()
                .body()
                .as(PetToStoreResponse[].class);
    }

    @Step("Get pet by statuses")
    public <T> T getPetById(int statusCode, String petId, Class<T> expectedClass) {
        return petController.getPetById(petId)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(expectedClass);
    }

    @Step("Update pet in store")
    public PetResponse updatePetInStore(int statusCode, String petId, String name, String status) {
        return petController.updatePetInStore(petId, name, status)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(PetResponse.class);
    }

    @Step("Delete pet")
    public PetResponse deletePet(int statusCode, String petId, String apiKey) {
        return petController.deletePet(petId, apiKey)
                .statusCode(statusCode)
                .extract()
                .body()
                .as(PetResponse.class);
    }

    @Step("Delete no existing pet")
    public JsonPath deleteNoExistingPet(int statusCode, String petId, String apiKey) {
        return petController.deletePet(petId, apiKey)
                .statusCode(statusCode)
                .extract().jsonPath();
    }
}
