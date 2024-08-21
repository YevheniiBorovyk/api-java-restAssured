package tests.store;

import com.rest.models.store.requests.CreateStoreOrderRequest;
import com.rest.models.store.responses.ErrorBodyResponse;
import com.rest.step.StoreSteps;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreNegativeTests {

    private final StoreSteps storeSteps = new StoreSteps();

    @Test(description = "Create store order with empty body")
    public void createStoreOrderWithEmptyBody() {
        CreateStoreOrderRequest createStoreOrderRequest = new CreateStoreOrderRequest.Builder().build();

        try {
            storeSteps.createStoreOrder(200, createStoreOrderRequest);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Get store order by id with wrong id")
    public void getStoreOrderByIdWitWrongId() {
        ErrorBodyResponse response = storeSteps.getStoreOrderById(404, 1234111111, ErrorBodyResponse.class);
        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getType()).isEqualTo("error");
        assertThat(response.getMessage()).isEqualTo("Order not found");
    }

    @Test(description = "Delete store order by id with wrong id")
    public void deleteStoreOrderByIdWithWrongId() {
        ErrorBodyResponse response = storeSteps.deleteStoreOrderByIdWithError(404, 1234111111);
        assertThat(response.getCode()).isEqualTo(404);
        assertThat(response.getType()).isEqualTo("unknown");
        assertThat(response.getMessage()).isEqualTo("Order Not Found");
    }
}
