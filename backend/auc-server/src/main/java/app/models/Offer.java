package app.models;

import app.repository.Identifiable;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Offer implements Identifiable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private LocalDate sellDate;
    private double valueHighestBid;
    @OneToMany(mappedBy = "offer", cascade = CascadeType.MERGE)
   @JsonIncludeProperties({"idBid", "bidValue"})
    private List<Bid> bids = new ArrayList<>();

    public Offer(Long id, String title, Status status, String description, LocalDate sellDate, double valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public Offer(){

    }



    public static Offer createSampleOffer(long id){
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
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public List<Bid> setBids() {
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

    public Double getValueHighestBid() {
        if (this.bids.isEmpty()) {
            return null;
        }

        return this.bids.stream()
                .mapToDouble(Bid::getValue)
                .max()
                .orElse(0.0);
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
