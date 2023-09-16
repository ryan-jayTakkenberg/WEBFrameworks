<template>
  <div>
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
    <div v-if="selectedOffer">
      <h2>Selected Offer</h2>
      <p>ID: {{ selectedOffer.id }}</p>
      <p>Title: {{ selectedOffer.title }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      offers: [
        { id: 1, title: "Offer 1" },
        { id: 2, title: "Offer 2" },
        { id: 3, title: "Offer 3" },
        // Add more offers here
      ],
      selectedOffer: null,
      nextOfferId: 3, // Used to generate unique IDs for new offers
    };
  },
  methods: {
    toggleSelection(offer) {
      if (this.selectedOffer === offer) {
        this.selectedOffer = null; // Deselect if already selected
      } else {
        this.selectedOffer = offer;
      }
    },
    addNewOffer() {
      const newOffer = {
        id: this.nextOfferId++,
        title: `Offer ${this.nextOfferId}`,
      };
      this.offers.push(newOffer);
      this.selectedOffer = newOffer; // Automatically select the new offer
    },
  },
};
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
  font-size: 16px;
}

button:hover{
  background-color: sandybrown;
  cursor: pointer;
}
</style>
