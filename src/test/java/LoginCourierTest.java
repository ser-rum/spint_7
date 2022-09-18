import courier.Courier;
import courier.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {

    Courier courier;
    Courier courierWithoutPassword;
    CourierClient courierClient;
    String wrongLogin = "nonexistent";
    String wrongPassword = "courier";

    @Before
    public void setup() {
        courier = Courier.getRandomCourier();
        courierWithoutPassword = Courier.getWithoutPassword();
        courierClient = new CourierClient();
        courierClient.create(courier);
    }

    @After
    public void teardown() {
        courierClient.delete(courier);
    }


    @Test
    public void courierCanLogin(){
        ValidatableResponse loginResponse = courierClient.login(courier);
        loginResponse.statusCode(200)
                .assertThat().body("id", notNullValue());
    }

    @Test
    public void canNotLoginWithWrongLoginAndPassword() {
        ValidatableResponse loginResponse =
                courierClient.wrongCredentialsLogin(wrongLogin, wrongPassword);
        loginResponse.statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void canNotLoginWithoutAllFields() {
        ValidatableResponse response = courierClient.login(courierWithoutPassword);
        response.statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}