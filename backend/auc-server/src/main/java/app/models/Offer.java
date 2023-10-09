package app.models;

import java.time.LocalDate;
import java.util.List;

public class Offer {
    private int id;
    private String title;
    private String status;
    private String description;
    private LocalDate sellDate;
    private double valueHighestBid;

    public Offer(int id, String title, String status, String description, LocalDate sellDate, double valueHighestBid) {
        super();
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
        String status = "Status: " + id;
        LocalDate startDate = LocalDate.now().plusDays(id);
        double price = Math.random() * 100;

        return new Offer(id, title, status, description, startDate, price);

    }
    public Offer(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
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

    public void setStatus(String status) {
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
}
