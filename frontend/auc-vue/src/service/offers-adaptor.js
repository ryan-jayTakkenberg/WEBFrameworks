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
        //GET request on the /offers resource
        // endpoint
        // awaits resolution of the JSON response
        return offersData?.map(Offer.copyConstructor());
        //map plain JSON data-only objects
        // into true instances of the Book class
    }

    async asyncFindById(id) {
        console.log('OffersAdaptor.asyncFindById(' + id + ')...');
        return this.fetchJson(this.resourcesUrl + "/" + id);
    }

    async asyncSave(offer) {
        console.log('OffersAdaptor.asyncSave(' + offer + ')...');
        return this.fetchJson(this.resourcesUrl, {
            method: 'POST',
            body: JSON.stringify(offer),
            headers: {
                'Content-Type': 'application/json'
            }
        });
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