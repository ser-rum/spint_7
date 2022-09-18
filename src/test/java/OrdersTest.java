import io.restassured.response.ValidatableResponse;
import order.OrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.hasKey;

@RunWith(Parameterized.class)
public class OrdersTest {

    private final String[] color;

    public OrdersTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][] {
                {new String[]{"\"BLACK\""}},
                {new String[]{"\"GREY\""}},
                {new String[]{"\"BLACK\"", "\"GREY\""}},
                {new String[]{}}
        };
    }

    @Test
    public void orderCanBeCreated() {
        OrderClient order = new OrderClient(color);
        ValidatableResponse response = order.create();
        response.statusCode(201)
                .assertThat().body("$", hasKey("track"));
    }
}