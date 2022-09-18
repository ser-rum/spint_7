import courier.Courier;
import courier.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    Courier courier;
    Courier courierWithoutPassword;
    CourierClient courierClient;

    @Before
    public void setup() {
        courier = Courier.getRandomCourier();
        courierWithoutPassword = Courier.getWithoutPassword();
        courierClient = new CourierClient();
    }

    @After
    public void teardown() {
        courierClient.delete(courier);
    }


    @Test
    public void courierCanBeCreated(){
        ValidatableResponse response = courierClient.create(courier);
        response.statusCode(201)
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    public void canNotCreateTwoSameCouriers() {
        courierClient.create(courier);
        ValidatableResponse sameCourierResponse = courierClient.create(courier);
        sameCourierResponse.statusCode(409)
                            .assertThat().body("message", equalTo("Этот логин уже используется. " +
                            "Попробуйте другой."));
    }

    @Test
    public void canNotCreateCourierWithoutAllFields() {
        ValidatableResponse response = courierClient.create(courierWithoutPassword);
        response.statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для " +
                        "создания учетной записи"));
    }
}