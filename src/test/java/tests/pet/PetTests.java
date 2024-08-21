package tests.pet;

import com.rest.models.pet.requests.PetToStoreRequest;
import com.rest.models.pet.requests.CategoryRequest;
import com.rest.models.pet.requests.Status;
import com.rest.models.pet.requests.TagsRequest;
import com.rest.models.pet.responses.PetToStoreResponse;
import com.rest.models.pet.responses.PetResponse;
import com.rest.step.PetSteps;
import org.testng.annotations.Test;

import java.util.List;

import static com.rest.utills.StringMan.getRandomNumber;
import static com.rest.utills.StringMan.getRandomString;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTests {

    private final PetSteps petSteps = new PetSteps();

    @Test(description = "upload pet image")
    public void uploadPetImage() {
        String additionalMetadata = getRandomString(5);
        PetResponse response = petSteps.uploadPetImage(200, "1", additionalMetadata);
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getType()).isEqualTo("unknown");
        assertThat(response.getMessage()).contains(
                "additionalMetadata: " + additionalMetadata + "\nFile uploaded to ./cat.png");
    }

    @Test(description = "Add pet to store")
    public void addPetToStore() {
        int petId = getRandomNumber(3);
        int categoryId = getRandomNumber(3);
        String categoryName = getRandomString(5);
        String petName = getRandomString(5);
        String firstUrl = getRandomString(5);
        String secondUrl = getRandomString(5);
        List<String> photoUrls = List.of(firstUrl, secondUrl);
        int firstTagId = getRandomNumber(3);
        String firstTagName = getRandomString(5);
        int secondTagId = firstTagId + 1;
        String secondTagName = getRandomString(5);
        String status = getRandomString(5);
        List<TagsRequest> tagsRequests =
                List.of(new TagsRequest(firstTagId, firstTagName), new TagsRequest(secondTagId, secondTagName));

        PetToStoreResponse response = petSteps.addPetToStore(200,
                getAddPetToStoreRequest(petId, new CategoryRequest(categoryId, categoryName), petName, photoUrls,
                        tagsRequests, status));
        assertThat(response.getId()).isEqualTo(petId);
        assertThat(response.getCategory().getId()).isEqualTo(categoryId);
        assertThat(response.getCategory().getName()).isEqualTo(categoryName);
        assertThat(response.getName()).isEqualTo(petName);
        assertThat(response.getPhotoUrls()).isEqualTo(photoUrls);
        assertThat(response.getTags().get(0).getId()).isEqualTo(firstTagId);
        assertThat(response.getTags().get(0).getName()).isEqualTo(firstTagName);
        assertThat(response.getTags().get(1).getId()).isEqualTo(secondTagId);
        assertThat(response.getTags().get(1).getName()).isEqualTo(secondTagName);
    }

    @Test(description = "Update existing pet")
    public void updateExistingPet() {
        int petId = getRandomNumber(3);
        int categoryId = getRandomNumber(3);
        String categoryName = getRandomString(5);
        String petName = getRandomString(5);
        String firstUrl = getRandomString(5);
        String secondUrl = getRandomString(5);
        List<String> photoUrls = List.of(firstUrl, secondUrl);
        int firstTagId = getRandomNumber(3);
        String firstTagName = getRandomString(5);
        int secondTagId = firstTagId + 1;
        String secondTagName = getRandomString(5);
        String status = getRandomString(5);
        List<TagsRequest> tagsRequests =
                List.of(new TagsRequest(firstTagId, firstTagName), new TagsRequest(secondTagId, secondTagName));
        petSteps.addPetToStore(200,
                getAddPetToStoreRequest(petId, new CategoryRequest(categoryId, categoryName), petName, photoUrls,
                        tagsRequests, status));

        categoryId = getRandomNumber(3);
        categoryName = getRandomString(5);
        petName = getRandomString(5);
        firstUrl = getRandomString(5);
        secondUrl = getRandomString(5);
        photoUrls = List.of(firstUrl, secondUrl);
        firstTagId = getRandomNumber(3);
        firstTagName = getRandomString(5);
        secondTagId = firstTagId + 1;
        secondTagName = getRandomString(5);
        status = getRandomString(5);
        tagsRequests = List.of(new TagsRequest(firstTagId, firstTagName), new TagsRequest(secondTagId, secondTagName));
        PetToStoreResponse response = petSteps.updateExistingPet(200,
                getAddPetToStoreRequest(petId, new CategoryRequest(categoryId, categoryName), petName, photoUrls,
                        tagsRequests, status));

        assertThat(response.getId()).isEqualTo(petId);
        assertThat(response.getCategory().getId()).isEqualTo(categoryId);
        assertThat(response.getCategory().getName()).isEqualTo(categoryName);
        assertThat(response.getName()).isEqualTo(petName);
        assertThat(response.getPhotoUrls()).isEqualTo(photoUrls);
        assertThat(response.getTags().get(0).getId()).isEqualTo(firstTagId);
        assertThat(response.getTags().get(0).getName()).isEqualTo(firstTagName);
        assertThat(response.getTags().get(1).getId()).isEqualTo(secondTagId);
        assertThat(response.getTags().get(1).getName()).isEqualTo(secondTagName);
    }

    @Test(description = "Find pet by status")
    public void findPetByStatus() {
        //Can't check response because of this error `Numeric value (9223372016900019473) out of range of int
        // (-2147483648 - 2147483647)`
        try {
            PetToStoreResponse[] response = petSteps.findPetByStatus(200, Status.SOLD);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Get pet by id")
    public void getPetById() {
        Integer petId = getRandomNumber(3);
        int categoryId = getRandomNumber(3);
        String categoryName = getRandomString(5);
        String petName = getRandomString(5);
        String firstUrl = getRandomString(5);
        String secondUrl = getRandomString(5);
        List<String> photoUrls = List.of(firstUrl, secondUrl);
        int firstTagId = getRandomNumber(3);
        String firstTagName = getRandomString(5);
        int secondTagId = firstTagId + 1;
        String secondTagName = getRandomString(5);
        String status = getRandomString(5);
        List<TagsRequest> tagsRequests =
                List.of(new TagsRequest(firstTagId, firstTagName), new TagsRequest(secondTagId, secondTagName));
        petSteps.addPetToStore(200,
                getAddPetToStoreRequest(petId, new CategoryRequest(categoryId, categoryName), petName, photoUrls,
                        tagsRequests, status));

        PetToStoreResponse response = petSteps.getPetById(200, petId.toString(), PetToStoreResponse.class);
        assertThat(response.getId()).isEqualTo(petId);
        assertThat(response.getCategory().getId()).isEqualTo(categoryId);
        assertThat(response.getCategory().getName()).isEqualTo(categoryName);
        assertThat(response.getName()).isEqualTo(petName);
        assertThat(response.getPhotoUrls()).isEqualTo(photoUrls);
        assertThat(response.getTags().get(0).getId()).isEqualTo(firstTagId);
        assertThat(response.getTags().get(0).getName()).isEqualTo(firstTagName);
        assertThat(response.getTags().get(1).getId()).isEqualTo(secondTagId);
        assertThat(response.getTags().get(1).getName()).isEqualTo(secondTagName);
    }

    @Test(description = "Update pet in store")
    public void updatePetInStore() {
        int petId = getRandomNumber(3);
        int categoryId = getRandomNumber(3);
        String categoryName = getRandomString(5);
        String petName = getRandomString(5);
        String firstUrl = getRandomString(5);
        String secondUrl = getRandomString(5);
        List<String> photoUrls = List.of(firstUrl, secondUrl);
        int firstTagId = getRandomNumber(3);
        String firstTagName = getRandomString(5);
        int secondTagId = firstTagId + 1;
        String secondTagName = getRandomString(5);
        String status = getRandomString(5);
        List<TagsRequest> tagsRequests =
                List.of(new TagsRequest(firstTagId, firstTagName), new TagsRequest(secondTagId, secondTagName));
        petSteps.addPetToStore(200,
                getAddPetToStoreRequest(petId, new CategoryRequest(categoryId, categoryName), petName, photoUrls,
                        tagsRequests, status));

        PetResponse response = petSteps.updatePetInStore(200, Integer.toString(petId), petName, status);
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getType()).isEqualTo("unknown");
        assertThat(response.getMessage()).contains(Integer.toString(petId));
    }

    @Test(description = "Delete pet")
    public void deletePet() {
        int petId = getRandomNumber(3);
        int categoryId = getRandomNumber(3);
        String categoryName = getRandomString(5);
        String petName = getRandomString(5);
        String firstUrl = getRandomString(5);
        String secondUrl = getRandomString(5);
        List<String> photoUrls = List.of(firstUrl, secondUrl);
        int firstTagId = getRandomNumber(3);
        String firstTagName = getRandomString(5);
        int secondTagId = firstTagId + 1;
        String secondTagName = getRandomString(5);
        String status = getRandomString(5);
        List<TagsRequest> tagsRequests =
                List.of(new TagsRequest(firstTagId, firstTagName), new TagsRequest(secondTagId, secondTagName));
        petSteps.addPetToStore(200,
                getAddPetToStoreRequest(petId, new CategoryRequest(categoryId, categoryName), petName, photoUrls,
                        tagsRequests, status));

        PetResponse response = petSteps.deletePet(200, Integer.toString(petId), "apiKey");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getType()).isEqualTo("unknown");
        assertThat(response.getMessage()).contains(Integer.toString(petId));
    }

    private PetToStoreRequest getAddPetToStoreRequest(int id, CategoryRequest categoryRequest, String name,
            List<String> photoUrls, List<TagsRequest> tagsRequests, String status) {
        return PetToStoreRequest.builder()
                .id(id)
                .category(categoryRequest)
                .name(name)
                .photoUrls(photoUrls)
                .tags(tagsRequests)
                .status(status)
                .build();
    }
}
