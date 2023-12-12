package app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class APIConfig implements WebMvcConfigurer {

    public Set<String> SECURED_PATHS =
            Set.of("/offers");

    @Value("${jwt.issuer:private company}")
    private String issuer;

    @Value("${jwt.passphrase:This is very secret information for my private encryption key that nobody can know.}")
    private String passphrase;

    @Value("${jwt.duration-of-validity:1200}") // default 20 minutes;
    private int tokenDurationOfValidity;

    public String getIssuer() {
        // include a reboot sequence nr in the issuer signature
        //  such that authentication tokens can be revoked after a reboot.
        return String.format(this.issuer);
    }

    public String getPassphrase() {
        return passphrase;
    }

    public int getTokenDurationOfValidity() {
        return tokenDurationOfValidity;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public void setTokenDurationOfValidity(int tokenDurationOfValidity) {
        this.tokenDurationOfValidity = tokenDurationOfValidity;
    }
}
