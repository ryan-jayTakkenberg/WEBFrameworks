package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.JWT.JWTConfig;
import app.JWT.JWToken;
import app.models.Offer;
import app.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {




    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody ObjectNode requestBody) {
        String email = requestBody.get("email").asText();
        String password = requestBody.get("password").asText();

        // Perform authentication logic
        if (isValidLogin(email, password)) {
            // For simplicity, creating a new User object on successful login
            User user = new User(extractNameFromEmail(email), email, password);
            // Issue a token for the account, valid for some time
            JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());
            String tokenString = jwToken.encode(JWTConfig.getInstance().getIssuer(),
                    JWTConfig.getInstance().getPassphrase(),JWTConfig.getInstance().getExpiration());

            return ResponseEntity.accepted().header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString).body(user);
        } else {
            throw new PreConditionFailedException("Login failed cannot login in email=" + email);
        }
    }

    private boolean isValidLogin(String email, String password) {
        // For simplicity, check if the password is the same as the username before the '@' character
        String username = email.split("@")[0];
        return password.equals(username);
    }

    private String extractNameFromEmail(String email) {
        // For simplicity, extract the name from the start of the email address
        return email.split("@")[0];
    }


}




