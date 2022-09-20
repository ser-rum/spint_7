import courier.Courier;
import courier.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    Courier courier;
    CourierClient courierClient;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @After
    public void teardown() {
        courierClient.delete(courier);
    }


    @Test
    public void courierCanBeCreated(){
        courier = Courier.getCourier();
        ValidatableResponse response = courierClient.create(courier);
        response.statusCode(201)
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    public void canNotCreateTwoSameCouriers() {
        courier = Courier.getCourier();
        courierClient.create(courier);
        ValidatableResponse sameCourierResponse = courierClient.create(courier);
        sameCourierResponse.statusCode(409)
                            .assertThat().body("message", equalTo("Этот логин уже используется. " +
                            "Попробуйте другой."));
    }

    @Test
    public void canNotCreateCourierWithoutAllFields() {
        ValidatableResponse response = courierClient.create(Courier.getWithoutLoginAndPassword());
        response.statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для " +
                        "создания учетной записи"));
    }

    @Test
    public void canNotCreateCourierWithoutLogin() {
        ValidatableResponse response = courierClient.create(Courier.getWithoutLogin());
        response.statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для " +
                        "создания учетной записи"));
    }

    @Test
    public void canNotCreateCourierWithoutPassword() {
        ValidatableResponse response = courierClient.create(Courier.getWithoutPassword());
        response.statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для " +
                        "создания учетной записи"));
    }
}