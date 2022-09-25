package courier;

import base.BaseClient;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends BaseClient {

    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";
    private final String wrongLogin = "nonexistent";
    private final String wrongPassword = "courier";


    public ValidatableResponse create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse login (Courier courier) {
        return getSpec()
                .body(new CourierCredentials(courier).getCourierCredentials())
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public ValidatableResponse loginWithWrongLogin (Courier courier) {
        return getSpec()
                .body(new CourierCredentials(wrongLogin, courier).getCourierCredentials())
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public ValidatableResponse loginWithWrongPassword (Courier courier) {
        return getSpec()
                .body(new CourierCredentials(courier, wrongPassword).getCourierCredentials())
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public ValidatableResponse loginWithWrongLoginAndPassword () {
        return getSpec()
                .body(new CourierCredentials(wrongLogin, wrongPassword).getCourierCredentials())
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public void delete(Courier courier) {
        if (courier != null) {
            getSpec()
                    .when()
                    .delete(ROOT + "/:" + courierId(courier).toString());
        }
    }

    public Integer courierId(Courier courier) {
        return login(courier)
                .extract()
                .path("id");
    }
}