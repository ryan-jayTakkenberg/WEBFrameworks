
export class Offer {
    id;
    title;
    status;
    description;
    _sellDate;
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
        this.valueHighestBid = valueHighestBid;
        this.sellDate = sellDate;
    }

    get sellDate() {
        return `${this._sellDate.getFullYear()}-` +
        `${this._sellDate.getMonth()+1}-`+
        `${this._sellDate.getDate()}`
    }

    set sellDate(value) {
        this._sellDate = new Date(value)
    }

    static createSampleOffer(pId = 0) {
        const id = pId;
        const title = "A great article offer";
        const statusValues = Object.values(Offer.Status);
        const status = statusValues[Math.floor(Math.random() * statusValues.length)];
        const description = "dummyDescription";

        const valueBid = Math.floor(Math.random() * 1000);
        const valueHighestBid = status === Offer.Status.NEW ? null : `${valueBid}`;

        const today = new Date();
        const startDate = new Date(today - 30 * 24 * 60 * 60 * 1000);
        const randomTimestamp = startDate.getTime() + Math.random() * (today - startDate.getTime());
        const randomDate = new Date(randomTimestamp);

        const year = randomDate.getFullYear();
        const month = String(randomDate.getMonth() + 1).padStart(2, '0');
        const day = String(randomDate.getDate()).padStart(2, '0');

        const sellDate = `${year}-${month}-${day}`;

        return new Offer(id, title, status, description, sellDate, valueHighestBid);
    }

    static copyConstructor(offer) {
        try {
            if (offer == null) return null;
            let copy = Object.assign(new Offer(), offer);
            copy.sellDate = new Date(offer.sellDate);
            return copy;
        } catch (error) {
            console.error('Fout bij het kopiëren van het aanbod:', error);
            return null;
        }
    }
    toJsonString(){
        return JSON.stringify({
            id: this.id,
            title: this.title,
            status: this.status,
            description: this.description,
            sellDate: this.sellDate,
            valueHighestBid: this.valueHighestBid
        })
    }
}
