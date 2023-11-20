package app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
public class Bid {
    @Id
    @GeneratedValue()
    private Long idBid;

    private double bidValue;
    @ManyToOne
//    @JsonManagedReference remove this than the post works
    private Offer offer;
    public Bid() {

    }


    public Bid(double value ) {
        this.bidValue = value;
    }


    public boolean associateOffer(Offer offer) {
        if (offer != null && this.getOffer() == null) {
            // Debugging statement
            System.out.println("Associating Bid with Offer: Bid ID = " + this.getId() + ", Offer ID = " + offer.getId());
            offer.associateBid(this);
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
        return bidValue;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Long getIdBid() {
        return idBid;
    }

    public void setIdBid(Long idBid) {
        this.idBid = idBid;
    }

    public double getBidValue() {
        return bidValue;
    }

    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }
}
