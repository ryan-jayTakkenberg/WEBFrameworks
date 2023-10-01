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

    static DateFormat = {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    };

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
        const sellDate = date.toLocaleDateString("en-US", this.DateFormat);

        return new Offer(id, title, status, description, sellDate, valueHighestBid);
    }

    static copyConstructor(offer){
        if (offer == null) return null;
        let copy = Object.assign(new Offer(), offer);
        copy.sellDate = new Date(offer.sellDate).toLocaleDateString("en-US", Offer.DateFormat);
        return copy;
    }


}
