package app.models;

import app.repository.Identifiable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
public class Bid implements Identifiable {
    @Id
    @GeneratedValue()
    private long idBid;

    private double bidValue;
    @ManyToOne
    @JsonBackReference
    private Offer offer;

    @ManyToOne
    @JsonIgnoreProperties(value = {"bids", "email", "hashedPassword", "role"})
    @JsonProperty("madeBy")
    private User user;




    public Bid() {

    }


    public Bid(double value ) {
        this.bidValue = value;
    }


    public boolean associateOffer(Offer offer) {
        if (offer != null && this.getOffer() == null) {
            offer.addBid(this);
            this.setOffer(offer);
            return true;
        }
        return false;
    }

    public boolean associateUser(User user) {
        if (user != null && this.getUser() == null) {
            user.addBid(this);
            this.setUser(user);
            return true;
        }
        return false;
    }


    public long getId() {
        return idBid;
    }

    @Override
    public void setId(long id) {

    }

    public double getValue() {
        return bidValue;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public long getIdBid() {
        return idBid;
    }

    public void setIdBid(Long idBid) {
        this.idBid = idBid;
    }

    public double getBidValue() {
        return bidValue;
    }

    public void setIdBid(long idBid) {
        this.idBid = idBid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }
}
