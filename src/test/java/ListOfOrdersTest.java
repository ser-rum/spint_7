import io.restassured.response.ValidatableResponse;
import order.OrderClient;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class ListOfOrdersTest {

    @Test
    public void orderCanBeCreated() {
        OrderClient order = new OrderClient();
        ValidatableResponse response = order.getListOfOrders();
        response.assertThat().body("orders", notNullValue());
    }
}