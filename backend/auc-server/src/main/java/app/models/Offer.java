package app.models;

import java.time.LocalDate;

public class Offer {
    private int id;
    private String title;
    private String status;
    private String description;
    private LocalDate sellDate;
    private double valueHighestBid;

    public Offer(int id, String title, String status, String description, LocalDate sellDate, double valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
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
}
