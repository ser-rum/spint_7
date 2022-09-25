package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private final String login;
    private final String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getCourier() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "P@ssw0rd",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getWithoutLogin() {
        return new Courier(
                "",
                "P@ssw0rd",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getWithoutPassword() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getWithoutLoginAndPassword() {
        return new Courier(
                "",
                "",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}