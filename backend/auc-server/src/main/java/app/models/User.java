package app.models;

import app.repository.Identifiable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Entity
@Table(name = "app_user")
public class User implements Identifiable {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String email;
    private String hashedPassword;
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Bid> bids = new ArrayList<>();

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
    public List<Bid> setBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setUser(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setUser(null);
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
