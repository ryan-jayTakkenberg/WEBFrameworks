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
            <th>Highest bid:</th>
            <th>Made By:</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="offer in offers" :key="offer.id" :class="{ selected: offer === selectedOffer }">
            <td @click="onSelect(offer)" class="overviewTableContent">
              {{ offer.title }} - {{ offer.id }}
            </td>
            <td>{{offer.valueHighestBid}}</td>
            <td>{{offer.bids[0].madeBy.name}}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="right-content">
        <router-view
            :offerList="offers"
            :selectedOffer="selectedOffer"
            @deselect="deselectOffer"
            @update-offer-list="getOfferList"
        ></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "offersOverview37",
  inject:  ['offersService'],
  data() {
    return {
      offers: [],
      selectedOffer: null,
    };
  },
  async created() {
    await this.getOfferList();
    if (this.$route.params.id){
      this.selectedOffer = await this.offersService.asyncFindById(parseInt(this.$route.params.id));
    }
  },
  methods: {
    onSelect(offer) {
      if (this.selectedOffer === offer) {
        this.selectedOffer = null;
        this.$router.push(this.$route.matched[0].path);
      } else {
        this.selectedOffer = offer;
        console.log(this.selectedOffer)
        this.$router.push(this.$route.matched[0].path + "/" + offer.id);
      }
    },
    deselectOffer() {
      this.selectedOffer = null;
    },
    async getOfferList(){
      this.offers = await this.offersService.asyncFindAllForSale();
    }
  },
  watch: {
    '$route.params.id'() {
      if (!this.$route.params.id){
        this.deselectOffer()
      }
    }
  }
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
