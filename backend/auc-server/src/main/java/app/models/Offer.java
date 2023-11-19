package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Offer {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private LocalDate sellDate;
    private double valueHighestBid;
    @OneToMany(mappedBy = "offer")
    @JsonBackReference
    private Set<Bid> bids = new HashSet<>();

    public Offer(int id, String title, Status status, String description, LocalDate sellDate, double valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public Offer(){

    }



    public static Offer createSampleOffer(int id){
        String title = "Exercise " + id;
        String description = "Description for Offer id: " + id;
        Status status = Status.values()[(int)(Math.random() * Status.values().length)];
        LocalDate startDate = LocalDate.now().plusDays(id);
        double price = Math.random() * 100;

        return new Offer(id, title, status, description, startDate, price);

    }

    public enum Status {
        NEW("NEW"),
        FOR_SALE("FOR_SALE"),
        SOLD("SOLD"),
        PAID("PAID"),
        DELIVERED("DELIVERED"),
        CLOSED("CLOSED"),
        EXPIRED("EXPIRED"),
        WITHDRAWN("WITHDRAWN");

        private final String status;

        Status(String status) {
            this.status = status;
        }

    }
    public Offer(String title) {

        this.title = title;
        Status status = Status.values()[(int)(Math.random() * Status.values().length)];
        this.status = status;
    }


    @JsonView(Views.Summary.class)
    public int getId() {
        return id;
    }
    public Set<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setOffer(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setOffer(null);
    }

    public void associateBid(Bid bid) {
        if (bid != null && !bids.contains(bid)) {
            bids.add(bid);
            bid.setOffer(this);
        }
    }
    @JsonView(Views.Summary.class)
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public double getValueHighestBid() {
        return valueHighestBid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public void setValueHighestBid(double valueHighestBid) {
        this.valueHighestBid = valueHighestBid;
    }

    @JsonView(Views.Summary.class)
    public Status getStatus() {
        return status;
    }


}
