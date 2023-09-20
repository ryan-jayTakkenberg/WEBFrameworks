<template>
  <div class="Detail">
    <div class="titleHeader">
      <h2>Overview of all offered articles</h2>
    </div>
    <div class="content">
      <div class="left-content">
        <table>
          <thead>
          <tr>
            <th>Offer Title with ID</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="offer in offers" :key="offer.id" :class="{ selected: offer === selectedOffer }">
            <td  @click="toggleAndSelect(offer)"  class="overviewTableContent">
              {{ offer.title }} - {{ offer.id }}</td>
          </tr>
          </tbody>
        </table>
        <button @click="addNewOffer">New Offer</button>
      </div>
      <div class="right-content">
        <OffersDetail32
            :selectedOffer="selectedOffer"
            @delete-offer="handleDeleteOffer"
            @update-sell-date="handleUpdateSellDate">
        </OffersDetail32>
      </div>
    </div>

  </div>
</template>

<script>
import OffersDetail32 from "@/components/offers/OffersDetail32.vue";
import {Offer} from "@/models/offers";

export default {
  components: {
    OffersDetail32,
  },
  data() {
    return {
      offers: [],
      offerId: 3000,
      selectedOffer: null,
    };
  }, created() {
    let amountOfOffers = 6;

    for (let i = 0; i < amountOfOffers; i++) {
      const newOffer = Offer.createSampleOffer(this.offerId);
      this.offers.push(newOffer);
      this.offerId += Math.floor(Math.random() * 3 + 1);
    }
  },
  methods: {
    toggleAndSelect(offer) {
      this.toggleSelection(offer);
      this.onSelect(offer);
    },
    // dit is voor de select - deslect
    toggleSelection(offer) {
      if (this.selectedOffer === offer) {
        this.selectedOffer = null;
      } else {
        this.selectedOffer = offer;
      }
    },
    addNewOffer() {
      const newOffer = Offer.createSampleOffer(this.offerId);
      this.offers.push(newOffer);
      this.offerId += Math.floor(Math.random() * 3 + 1);
      this.selectedOffer = newOffer; // autmomatisch selecteren nieuwe offer
    },
    handleDeleteOffer(offerToDelete) {
      // Remove the offer from the list
      this.offers = this.offers.filter((offer) => offer.id !== offerToDelete.id);

      // Unselect the deleted offer
      if (this.selectedOffer && this.selectedOffer.id === offerToDelete.id) {
        this.selectedOffer = null;
      }
    },
    handleUpdateSellDate(isoString) {
      if (this.selectedOffer) {
        this.selectedOffer.sellDate = isoString;
      }
    },onSelect(offer) {
      console.log(offer);
      if (offer !== this.selectedOffer && offer != null) {
        console.log("eerste"
        )
        // If another offer is selected, update 'selectedOffer' and navigate to the corresponding route
        this.$router.push(this.$route.matched[0].path + "/" + offer.id);
      } else if (this.selectedOffer != null) {
        console.log("tweede")
        // Navigate to the parent path to unselect the selectedOffer
        this.$router.push(this.$route.matched[0].path); // Navigate to the parent path
      }
    },findSelectedFromRouteParam(id) {
      if (!id) return null;
      return this.offers.find((offer) => offer.id === parseInt(id));
    },
  },watch: {
    '$route'() {
      const id = this.$route.params.id; // Get the 'id' parameter from the route
      if (id !== undefined) {
        // Check if 'id' is defined in the route parameters
        this.selectedOffer = this.findSelectedFromRouteParam(id);
      }
    },
  },
};
</script>

<style scoped>
.overviewTableContent:hover{
  cursor: pointer;
  background-color: pink;
}

.Detail {
  display: flex;
  flex-direction: column;
}

.content{
  display: flex;
  flex-direction: row;
}

.left-content {
  width: 25%;
  margin-right: 5px;
}

.right-content {
  width: 75%;
  margin-left: 5px;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  border: 1px solid black;
  padding: 5px;
}

tr:nth-child(even) {
  background-color: #e8e8e8;
}

th {
  background-color: pink;
}

button {
  margin: 10px 10px 10px 0;
  height: 35px;
  width: 150px;
  background-color: pink;
  position: relative;
  float: left;
  border: none;
  font-size: 16px;
}

button:hover {
  background-color: sandybrown;
  cursor: pointer;
}

tr.selected {
  background-color: pink;
}
</style>
