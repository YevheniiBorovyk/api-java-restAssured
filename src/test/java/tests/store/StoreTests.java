package tests.store;

import com.rest.models.store.requests.CreateStoreOrderRequest;
import com.rest.models.store.responses.CreateStoreOrderResponse;
import com.rest.models.store.responses.ErrorBodyResponse;
import com.rest.step.StoreSteps;
import com.rest.utills.StringMan;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreTests {

    private final StoreSteps storeSteps = new StoreSteps();

    @Test(description = "Get pet inventory")
    public void getPetInventory() {
        storeSteps.getPetInventory(200);
    }

    @Test(description = "Create store order")
    public void createStoreOrder() {
        int id = StringMan.getRandomNumber(3);
        int petId = StringMan.getRandomNumber(3);
        int quantity = StringMan.getRandomNumber(3);

        CreateStoreOrderResponse response =
                storeSteps.createStoreOrder(200, getStoreOrderRequest(id, petId, quantity));
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getPetId()).isEqualTo(petId);
        assertThat(response.getQuantity()).isEqualTo(quantity);
    }

    @Test(description = "Get store order by id")
    public void getStoreOrderById() {
        int id = StringMan.getRandomNumber(3);
        int petId = StringMan.getRandomNumber(3);
        int quantity = StringMan.getRandomNumber(3);
        storeSteps.createStoreOrder(200, getStoreOrderRequest(id, petId, quantity));

        CreateStoreOrderResponse response = storeSteps.getStoreOrderById(200, id, CreateStoreOrderResponse.class);
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getPetId()).isEqualTo(petId);
        assertThat(response.getQuantity()).isEqualTo(quantity);
        assertThat(response.isComplete()).isTrue();
    }

    @Test(description = "Delete store order by id")
    public void deleteStoreOrderById() {
        int id = StringMan.getRandomNumber(3);
        int petId = StringMan.getRandomNumber(3);
        int quantity = StringMan.getRandomNumber(3);
        storeSteps.createStoreOrder(200, getStoreOrderRequest(id, petId, quantity));

        storeSteps.deleteStoreOrderById(200, id);
        ErrorBodyResponse response = storeSteps.getStoreOrderById(404, id, ErrorBodyResponse.class);
        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getType()).isEqualTo("error");
        assertThat(response.getMessage()).isEqualTo("Order not found");
    }

    private CreateStoreOrderRequest getStoreOrderRequest(int id, int petId, int quantity) {
        return new CreateStoreOrderRequest.Builder().setDefaultValues()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .build();
    }
}
