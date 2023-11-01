import {Offer} from "@/models/offers";

export class OffersAdaptor {
    // i export the class with the name OffersAdaptor
    resourcesUrl;// own variable for the url

    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        // here i give the url to the variable
        console.log("Created OffersAdaptor for " + resourcesUrl)
    }


// generic helper method
    async fetchJson(url, options = null) {
        let response = await fetch(url, options)
        // the url with the options save in a varibale
        if (response.ok) {
            return await response.json();// here wait for the respons from
            // the endpoint with the json file
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;// if the response wasn't .ok then console log why
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

    async asyncFindById(id) {
        console.log('OffersAdaptor.asyncFindById(' + id + ')...');
        return this.fetchJson(this.resourcesUrl + "/" + id);
    }

    async asyncSave(offer) {
        let url = `${this.resourcesUrl}`;

        if (offer.id !== 0) {
            // Append the offer ID to the URL if it's not zero
            url += `/${offer.id}`;
        }else {
            url += `/offers`;

        }

        const method = offer.id === 0 ? 'POST' : 'PUT';

        const options = {
            method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(offer)
        };

        const response = await this.fetchJson(url, options);

        if (method === 'POST' && response.id) {
            // If it's a POST request and the response contains the 'id', update the offer's 'id'
            offer.id = response.id;
        }

        return response;
    }



    async asyncDeleteById(id) {
        console.log('OffersAdaptor.asyncDeleteById(' + id + ')...');
        return this.fetchJson(this.resourcesUrl + "/" + id,
            {
                method: 'DELETE'
            }) // the standard method is get
        // so by a getter you dont have to type mehtod: GET
    }


}