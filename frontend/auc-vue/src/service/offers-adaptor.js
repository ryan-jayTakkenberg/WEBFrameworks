import {Offer} from "@/models/offers";

export class OffersAdaptor {
    resourcesUrl;

    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        console.log("Created OffersAdaptor for " + resourcesUrl)
    }


    async fetchJson(url, options = null) {
        let response = await fetch(url, options)
        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncFindAll() {
        console.log('OffersAdaptor.asyncFindAll()...');
        const offersData = await this.fetchJson(this.resourcesUrl);
        if (Array.isArray(offersData)) {// checkt op array voorkomt daarom errors
            return offersData.map(Offer.copyConstructor);
        } else {
            console.error('Geen geldige JSON-array ontvangen.');
            return [];
        }
    }

    async asyncFindAllForSale() {
        const offersData = await this.fetchJson(this.resourcesUrl + "?status=FOR_SALE");
        if (Array.isArray(offersData)) {
            return offersData.map(Offer.copyConstructor);
        } else {
            console.error('Geen geldige JSON-array ontvangen.');
            return [];
        }
    }

    async asyncFindById(id) {
        console.log('OffersAdaptor.asyncFindById(' + id + ')...');
        return this.fetchJson(this.resourcesUrl + "/" + id);
    }

    async asyncSave(offer) {
        let url = `${this.resourcesUrl}`;
        let method = ""

        if (offer.id !== 0) {
            method = 'PUT'
            // Append the offer ID to the URL if it's not zero
            url += `/${offer.id}`;
        }else {
            method = 'POST'
        }

        const options = {
            method,
            headers: {
                'Content-Type': 'application/json',

            },
            body: offer.toJsonString()
        };

        return await this.fetchJson(url, options);
    }

    async asyncDeleteById(id) {
        console.log('OffersAdaptor.asyncDeleteById(' + id + ')...');
        return this.fetchJson(this.resourcesUrl + "/" + id,
            {
                method: 'DELETE'
            })
    }
}
