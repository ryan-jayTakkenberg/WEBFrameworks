package app.models;

import app.repository.Identifiable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    public Bid() {

    }


    public Bid(double value ) {
        this.bidValue = value;
    }


    public boolean associateOffer(Offer offer) {
        if (offer != null && this.getOffer() == null) {
            // Debugging statement
            System.out.println("Associating Bid with Offer: Bid ID = " + this.getId() + ", Offer ID = " + offer.getId());
            // Update both sides of the association
            offer.addBid(this);
            this.setOffer(offer);

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

    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }
}
