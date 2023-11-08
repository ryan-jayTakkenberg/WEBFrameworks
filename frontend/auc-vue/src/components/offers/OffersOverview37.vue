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
            <td @click="onSelect(offer)" class="overviewTableContent">
              {{ offer.title }} - {{ offer.id }}
            </td>
          </tr>
          </tbody>
        </table>
        <button @click="addNewOffer">New Offer</button>
      </div>
      <div class="right-content">
        <router-view
            :offerList="offers"
            :selectedOffer="selectedOffer"
            @update-sell-date="handleUpdateSellDate"
            @Select-parent-class="SelectParentClass"
            @update-offer-list="getOfferList"
        ></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import {Offer} from "@/models/offers";

export default {
  name: "offersOverview37",
  inject:  ['offersService'],
  data() {
    return {
      offers: [],
      offerId: 3000,
      selectedOffer: null,
    };
  },async created() {
    await this.getOfferList();
  },
  methods: {
    onSelect(offer) {
      if (this.selectedOffer === offer) {
        this.selectedOffer = null;
        this.$router.push(this.$route.matched[0].path);
      } else {
        this.selectedOffer = offer;
        this.$router.push(this.$route.matched[0].path + "/" + offer.id);
      }
    },
    async addNewOffer() {
      const newOffer = Offer.createSampleOffer(0);

      try {
        const saveNewOffer = await this.offersService.asyncSave(newOffer);

        if (saveNewOffer.id) {
          // Update the offer's 'id' with the one returned by the server
          newOffer.id = saveNewOffer.id;
        }

        this.offers.push(newOffer);
        this.selectedOffer = newOffer;

        this.$router.push(this.$route.matched[0].path + "/" + newOffer.id);
      } catch (error) {
        console.log('Error while adding a new offer: ' + error);
      }
    },
    SelectParentClass(offerToCancel) {
      {
        if (this.selectedOffer && this.selectedOffer.id === offerToCancel.id) {
          this.selectedOffer = null;
          this.$router.push(this.$route.matched[0].path);
        }
      }
    },
    handleUpdateSellDate(dateString) {
      if (this.selectedOffer) {
        this.selectedOffer.sellDate = dateString;
      }
    },
    async getOfferList(){
      try {
        this.offers = await this.offersService.asyncFindAll();
        this.selectedOffer = this.onSelect(this.$route);
      } catch (error) {
        console.error('Fout bij het ophalen van aanbiedingen:', error);
      }
    }
  },
};
</script>

<style scoped>
.overviewTableContent:hover {
  cursor: pointer;
  background-color: pink;
}

.Detail {
  display: flex;
  flex-direction: column;
}

.content {
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
