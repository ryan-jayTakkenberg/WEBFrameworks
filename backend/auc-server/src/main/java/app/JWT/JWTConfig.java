package app.JWT;

public class JWTConfig {
    private static final JWTConfig INSTANCE = new JWTConfig();

    public static JWTConfig getInstance() {
        return INSTANCE;
    }

    public String getPassphrase() {
        return "This is an secret code it is extremly hard to get in 89387439982497034893823498-4-2- ";
    }

    public String getIssuer() {
        return "Team19";
    }

    public int getExpiration() {
        return 1200;
    }
}
