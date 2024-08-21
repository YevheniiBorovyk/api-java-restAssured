package tests.pet;

import com.rest.models.pet.requests.PetToStoreRequest;
import com.rest.models.pet.requests.CategoryRequest;
import com.rest.models.pet.requests.TagsRequest;
import com.rest.models.pet.responses.PetResponse;
import com.rest.step.PetSteps;
import org.testng.annotations.Test;

import java.util.List;

import static com.rest.utills.StringMan.getRandomNumber;
import static com.rest.utills.StringMan.getRandomString;
import static org.assertj.core.api.Assertions.assertThat;

public class PetNegativeTests {

    private final PetSteps petSteps = new PetSteps();

    @Test(description = "Add pet to store with existing id")
    public void addPetToStoreWithExistingId() {
        int petId = 1;
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
        //there should be a validation error here
    }

    @Test(description = "Update no existing pet")
    public void updateNoExistingPet() {
        petSteps.updateExistingPet(200,
                getAddPetToStoreRequest(1000000000, new CategoryRequest(1000000000, "name"), "petName", List.of("photoUrls"),
                        List.of(new TagsRequest(1000, "firstTagName")), "status"));
        //there should be a validation error
    }

    @Test(description = "Get no existing pet by id")
    public void getNoExistingPetById() {
        PetResponse response = petSteps.getPetById(404, "1000000", PetResponse.class);
        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getType()).isEqualTo("error");
        assertThat(response.getMessage()).isEqualTo("Pet not found");
    }

    @Test(description = "Update no existing pet in store")
    public void updateNoExistingPetInStore() {
        PetResponse response = petSteps.updatePetInStore(404, "1000000", "petName", "status");
        assertThat(response.getCode()).isEqualTo(404);
        assertThat(response.getType()).isEqualTo("unknown");
        assertThat(response.getMessage()).isEqualTo("not found");
    }

    @Test(description = "Delete no existing pet")
    public void deleteNoExistingPet() {
        petSteps.deleteNoExistingPet(404,"1000000", "apiKey");
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
