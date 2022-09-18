package courier;

import base.BaseClient;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends BaseClient {

    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";

    public ValidatableResponse create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse login(Courier courier) {
        return getSpec()
                .body(courierCredentials(courier))
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public ValidatableResponse wrongCredentialsLogin(String login, String password) {
        return getSpec()
                .body(courierCredentials(login, password))
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public void delete(Courier courier) {
        try {
            getSpec()
                    .when()
                    .delete(ROOT + "/:" + courierId(courier).toString());
        } catch (NullPointerException ignored) {
        }
    }

    public String courierCredentials(Courier courier) {
        return "{\"login\": \"" + courier.getLogin() + "\", \"password\": \""
                + courier.getPassword() + "\"}";
    }

    public String courierCredentials(String login, String password) {
        return "{\"login\": \"" + login + "\", \"password\": \""
                + password + "\"}";
    }

    public Integer courierId(Courier courier) {
        return login(courier)
                .extract()
                .path("id");
    }
}