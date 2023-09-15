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
        <th>Value Highest Bid</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="offer in offers" :key="offer.id">
        <td>{{offer.id}}</td>
        <td>{{offer.title}}</td>
        <td>{{offer.sellDate}}</td>
        <td>{{offer.status}}</td>
        <td>{{offer.valueHighestBid}}</td>
      </tr>
      </tbody>
    </table>
  </div>
  <button @click="onNewOffer()">New Offer</button>
</template>

<script>
import {Offer} from "@/models/offers";

export default {
  data(){
    return {
      offerId: 3000,
      offers: []
    };
  },
  created() {
    let amountOfOffers = 8;

    for (let i = 0; i < amountOfOffers; i++){
      const newOffer = Offer.createSampleOffer(this.offerId);
      this.offers.push(newOffer);
      this.offerId += Math.floor(Math.random() * 3 + 1);
    }
  },
  methods: {
    onNewOffer(){
      const newOffer = Offer.createSampleOffer(this.offerId);
      this.offers.push(newOffer);
      this.offerId += Math.floor(Math.random() * 3 + 1);
    }
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

button{
  margin: 10px;
  height: 35px;
  width: 150px;
  background-color: pink;
  position: relative;
  float: right;
  border: none;
}

button:hover{
  background-color: sandybrown;
  cursor: pointer;
}
</style>