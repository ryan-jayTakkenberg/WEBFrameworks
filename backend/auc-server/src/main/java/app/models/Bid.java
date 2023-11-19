package app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
public class Bid {
    @Id
    @GeneratedValue()
    private Long idBid;

    private double value;
    @ManyToOne
    @JsonManagedReference
    private Offer offer;
    public Bid(double value) {
        this.value = value;
    }

    public Bid() {
        // Default constructor
    }
    public boolean associateOffer(Offer offer) {
        if (offer != null && this.getOffer() == null) {
            // Update both sides of the association
            offer.addBid(this);
            this.setOffer(offer);
            return true;
        }
        return false;
    }



    public Long getId() {
        return idBid;
    }

    public double getValue() {
        return value;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
