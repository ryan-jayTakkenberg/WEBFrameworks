package app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Random;
import java.util.random.RandomGenerator;

@Entity
public class User {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String email;
    private String hashedPassword;
    private String role;

    public User( String name, String email, String hashedPassword) {
        this.id = (long) (Math.random() * 30);
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = "User";
    }

    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
