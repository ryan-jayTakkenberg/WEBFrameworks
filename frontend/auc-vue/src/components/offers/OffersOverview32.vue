

<template>
  <div class="Detail">
    <div class="left-content">
      <h1>Offers List</h1>
      <button @click="addNewOffer">New Offer</button>
      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-for="offer in offers"
            :key="offer.id"
            :class="{ selected: offer === selectedOffer }"
        >
          <td>{{ offer.id }}</td>
          <td>{{ offer.title }}</td>
          <td>
            <button @click="toggleSelection(offer)">
              {{ offer === selectedOffer ? 'Deselect' : 'Select' }}
            </button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="right-content">
      <div>
        <h2>Offer Details</h2>
        <OffersDetail32 :selectedOffer="selectedOffer"></OffersDetail32>
      </div>
    </div>
  </div>
</template>

<script>

 import OffersDetail32 from "@/components/offers/OffersDetail32.vue";


export default {
  components:{
    OffersDetail32,
  },
  data() {
    return {
      offers: [
        { id: 1, title: "Offer 1" },
        { id: 2, title: "Offer 2" },
        { id: 3, title: "Offer 3" },
        // dummy offers
      ],
      selectedOffer: null,
      nextOfferId: 3, // maakt nieuwe id's aan
    };
  },
  methods: {// dit is vooor de select - deslect
    toggleSelection(offer) {
      if (this.selectedOffer === offer) {
        this.selectedOffer = null;
      } else {
        this.selectedOffer = offer;
      }
    },
    addNewOffer() {
      const newOffer = {
        id: this.nextOfferId += 1, // als ik ++ deedt ging de id niet omhoog
        title: `Offer ${this.nextOfferId}`,
      }; // en deze maakt een nieuwe offer aan
      this.offers.push(newOffer);
      this.selectedOffer = newOffer; // autmomatisch selecteren nieuwe offer
    },
  },
};
</script>


<style scoped>

.Detail {
  display: flex;
  justify-content: space-between; /* Plaats de twee inhoudselementen aan de uiteinden */
  gap: 20px; /* Voeg wat ruimte toe tussen de twee inhoudselementen */
}
h2{
  margin-bottom: 88px;
}
.left-content {
  flex: 1;
}

.right-content {
  flex: 1; /* Maak beide inhoudselementen even breed */
}
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
  font-size: 16px;
}

button:hover{
  background-color: sandybrown;
  cursor: pointer;
}
</style>
