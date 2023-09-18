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
        const title = "A great article offer";
        const statusValues = Object.values(Offer.Status);
        const status = statusValues[Math.floor(Math.random() * statusValues.length)];
        const description = "dummyDescription";

        const valueBid = Math.floor(Math.random() * 1000);
        const valueHighestBid = status === Offer.Status.NEW ? null : `\u20AC ${valueBid}`;

        // formatting the date according to given example
        const date = new Date(Date.now() - 30 * 24 * 60 * 60 * 1000 + Math.random() * (30 * 24 * 60 * 60 * 1000));
        const day = date.toLocaleString('en-US', { weekday: 'short'});
        const month = date.toLocaleString('en-US', { month: 'short'});
        const dayOfMonth = date.getDate();
        const year = date.getFullYear();
        const hours = date.getHours().toString().padStart(2, '0');
        const minutes = date.getMinutes().toString().padStart(2, '0')

        const sellDate = `${day}, ${dayOfMonth} ${month} ${year}, ${hours}:${minutes}`;

        return new Offer(id, title, status, description, sellDate, valueHighestBid);
    }
}
