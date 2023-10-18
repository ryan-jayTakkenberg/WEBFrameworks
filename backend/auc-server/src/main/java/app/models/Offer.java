package app.models;

import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;

public class Offer {
    private int id;
    private String title;
    private Status status;
    private String description;
    private LocalDate sellDate;
    private double valueHighestBid;

    public Offer(int id, String title, Status status, String description, LocalDate sellDate, double valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
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
