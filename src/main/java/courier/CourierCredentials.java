package courier;

public class CourierCredentials {

    private final String login;
    private final String password;


    public CourierCredentials(Courier courier) {
        this.login = courier.getLogin();
        this.password = courier.getPassword();
    }

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials(String login, Courier courier) {
        this.login = login;
        this.password = courier.getPassword();
    }

    public CourierCredentials(Courier courier, String password) {
        this.login = courier.getLogin();
        this.password = password;
    }

    public String getCourierCredentials(){
        return "{\"login\": \"" + login + "\", \"password\": \""
                + password + "\"}";
    }
}
