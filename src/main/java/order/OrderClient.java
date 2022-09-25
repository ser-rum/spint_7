package order;

import base.BaseClient;
import io.restassured.response.ValidatableResponse;

import java.util.Arrays;

public class OrderClient extends BaseClient {

    String[] color;
    String json;
    private static final String ROOT = "/orders";
    private static final String TENORDERSFORCOURIER = "?limit=10&page=0";

    public OrderClient(String[] color) {
        this.color = color;
        this.json = "{\"firstName\": \"name\", \"lastName\": \"name\", \"address\": \"address\", " +
                "\"metroStation\": \"station\", \"phone\": \"+8 910 123 45 67\", \"rentTime\": \"1\", " +
                "\"deliveryDate\": \"2022-10-06\", \"comment\": \"comment\", \"color\": " +
                Arrays.toString(color) + "}";
    }

    public OrderClient() {
        this.json = "";
    }

    public ValidatableResponse create() {
        return getSpec()
                .body(json)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse getListOfOrders() {
        return getSpec()
                .body(json)
                .when()
                .get(ROOT + TENORDERSFORCOURIER)
                .then().log().all();
    }
}