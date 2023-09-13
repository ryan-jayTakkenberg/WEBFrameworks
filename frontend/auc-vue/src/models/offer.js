
const OfferStatus = {
    NEW: "NEW",
    FOR_SALE: "FOR_SALE",
    SOLD: "SOLD",
    PAID: "PAID",
    DELIVERED: "DELIVERED",
    CLOSED: "CLOSED",
    EXPIRED: "EXPIRED",
    WITHDRAWN: "WITHDRAWN",
};

class Offer {


    constructor(id, title, description, sellDate, status, valueHighestBid) {
        this.id = id;                     // Numeriek ID van de aanbieding
        this.title = title;               // Titel van de aanbieding
        this.description = description;   // Beschrijving van de aanbieding
        this.sellDate = sellDate;         // Verkoopdatum van de aanbieding (Date-object)
        this.status = status;             // Status van de aanbieding (gebruik OfferStatus)
        this.valueHighestBid = valueHighestBid; // Hoogste bodwaarde
    }
    public static createSampleOffer(pId = 0){

        const randomStatus = [Math.floor(Math.random())]
        const sellDate = Math.random().toString(Date)

        const valueHighestBid = [Math.floor(Math.random() * 1000)]



    }

}

export { Offer, OfferStatus };
