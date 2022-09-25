import courier.Courier;
import courier.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {

    Courier courier;
    CourierClient courierClient = new CourierClient();

    @Before
    public void setup() {
        courier = Courier.getCourier();
        courierClient.create(courier);
    }

    @After
    public void teardown() {
        courierClient.delete(courier);
    }


    @Test
    public void courierCanLogin(){
        courierClient.login(courier)
                .statusCode(200)
                .assertThat().body("id", notNullValue());
    }

    @Test
    public void canNotLoginWithWrongLoginAndPassword() {
        courierClient.loginWithWrongLoginAndPassword()
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void canNotLoginWithWrongLogin() {
        courierClient.loginWithWrongLogin(courier)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void canNotLoginWithWrongPassword() {
        courierClient.loginWithWrongPassword(courier)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void canNotLoginWithoutLogin() {
        courierClient.login(Courier.getWithoutLogin())
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void canNotLoginWithoutPassword() {
        courierClient.login(Courier.getWithoutPassword())
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void canNotLoginWithoutAllFields() {
        courierClient.login(Courier.getWithoutLoginAndPassword())
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}