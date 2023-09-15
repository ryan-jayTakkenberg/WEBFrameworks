export class Offer {
    id;
    title;
    status;
    description;
    sellDate;
    valueHighestBid;

    static Status = {
        NEW: "NEW",
        FOR_SALE: "FOR_SALE",
        SOLD: "SOLD",
        PAID: "PAID",
        DELIVERED: "DELIVERED",
        CLOSED: "CLOSED",
        EXPIRED: "EXPIRED",
        WITHDRAWN: "WITHDRAWN"
    }

    constructor(id, title, status, description, sellDate, valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    static createSampleOffer(pId = 0) {
        const id = pId;
        const title = "Sample Offer";
        const statusValues = Object.values(Offer.Status);
        const status = statusValues[Math.floor(Math.random() * statusValues.length)];
        const description = "Sample Description";
        const sellDate = new Date(Date.now() + Math.random() * (30 * 24 * 60 * 60 * 1000));
        const valueHighestBid = status === Offer.Status.NEW ? 0 : Math.floor(Math.random() * 1000);

        return new Offer(id, title, status, description, sellDate, valueHighestBid);
    }
}
