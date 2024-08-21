package com.rest.controllers;

import com.rest.models.pet.requests.PetToStoreRequest;
import com.rest.models.pet.requests.Status;
import com.rest.utills.data.Files;
import com.rest.utills.data.Services;
import com.rest.utills.data.Urls;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest.utills.SpecificationFactory.getDefaultRequestSpecification;

public class PetController {

    public ValidatableResponse UploadPetImage(String petId, String additionalMetadata) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .param(petId)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", Files.catFile)
                .contentType("multipart/form-data")
                .when()
                .post(String.format(Urls.PET_UPLOAD_IMAGE, petId))
                .then()
                .log()
                .all();
    }

    public ValidatableResponse addPetToStore(PetToStoreRequest petToStoreRequest) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .body(petToStoreRequest)
                .when()
                .post(Urls.PET)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse updateExitingPet(PetToStoreRequest petToStoreRequest) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .body(petToStoreRequest)
                .when()
                .put(Urls.PET)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse findPetByStatus(List<Status> statuses) {
        List<String> statusValues = statuses.stream()
                .map(Status::getValue)
                .collect(Collectors.toList());
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .queryParams("status", statusValues)
                .when()
                .get(Urls.PET_FIND_BY_STATUS)
                .then()
                .log()
                .all();
    }

    public ValidatableResponse getPetById(String petId) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .param(petId)
                .when()
                .get(String.format(Urls.PET_ID, petId))
                .then()
                .log()
                .all();
    }

    public ValidatableResponse updatePetInStore(String petId, String name, String status) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .param(petId)
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", name)
                .formParam("status", status)
                .when()
                .post(String.format(Urls.PET_ID, petId))
                .then()
                .log()
                .all();
    }

    public ValidatableResponse deletePet(String petId, String apiKey) {
        return RestAssured.given()
                .spec(getDefaultRequestSpecification(Services.PET_STORE.service))
                .param(petId)
                .header("api_key", apiKey)
                .when()
                .delete(String.format(Urls.PET_ID, petId))
                .then()
                .log()
                .all();
    }
}
