<template>
  <div>
    <h2>List of all offers:</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Selling Date</th>
        <th>Status</th>
        <th>Value Hihgest Bid</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="offer in offers" :key="offer.id">
        <td>{{offer.id}}</td>
        <td>{{offer.title}}</td>
        <td>{{offer.sellDate}}</td>
        <td>{{offer.status}}</td>
        <td v-if="offer.status !== 'NEW'">{{offer.valueHighestBid}}</td>
        <td v-else></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {Offer} from "@/models/offers";

export default {
  data(){
    return {
      offers: []
    };
  },
  created() {
    let offerId = 30000;
    let amountOfOffers = 8;

    for (let i = 0; i < amountOfOffers; i++){
      const newOffer = Offer.createSampleOffer(offerId);
      this.offers.push(newOffer);
      offerId = offerId + Math.floor(Math.random() * 3 + 1);
    }
  },
  methods: {
  }
}
</script>

<style scoped>
h2{
  text-align: center;
}

table{
  border-collapse: collapse;
  width: 100%;
}

th, td{
  border: 1px solid black;
  padding: 5px;
}

tr:nth-child(even){
  background-color: #e8e8e8;
}

th{
  background-color: pink;
}
</style>