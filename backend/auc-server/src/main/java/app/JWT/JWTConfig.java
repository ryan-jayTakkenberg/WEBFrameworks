package app.JWT;

public class JWTConfig {
    private static final JWTConfig INSTANCE = new JWTConfig();

    public static JWTConfig getInstance() {
        return INSTANCE;
    }

    public String getPassphrase() {
        return "This is very secret information for my private encryption key that nobody can know.";
    }

    public String getIssuer() {
        return "private company";
    }

    public int getExpiration() {
        return 1200;
    }
}
