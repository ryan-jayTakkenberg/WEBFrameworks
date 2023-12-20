<template>
  <div>
    <div v-if="!selectedOffer">
      <h2>Select an offer from the left</h2>
    </div>
    <div v-else>
      <table>
        <tbody>
        <tr>
          <th colspan="2">Offer details (id={{ copiedOffer.id }})</th>
        </tr>
        <tr>
          <th>Title:</th>
          <!-- eslint-disable vue/no-mutating-props-->
          <td><input type="text" class="inputfieldText" v-model="copiedOffer.title"></td>
        </tr>
        <tr>
          <th>Description:</th>
          <td><input type="text" class="inputfieldText" v-model="copiedOffer.description"></td>
        </tr>
        <tr>
          <th>Status:</th>
          <td>
            <select class="selectOptions" v-model="copiedOffer.status">
              <option v-for="status in offerStatusArray" :key="status" :value="status">{{ status }}</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>Sell date:</th>

          <td><input type="date" class="inputfieldDate"
                     v-model="copiedOffer.sellDate">
          </td>
        </tr>
        <tr>
          <th>Latest Bid:</th>
          <!-- eslint-disable vue/no-mutating-props -->
          <td><input type="text" class="inputfieldBid" v-model="copiedOffer.valueHighestBid"></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import {Offer} from "@/models/offers";

export default {
  name: "offersDetail37",
  inject:  ['offersService'],
  props: ['offerList'],
  emits: ['update-offer-list', 'deselect'],

  data() {
    return {
      offerStatusArray: Object.values(Offer.Status),
      selectedOffer: null,
      copiedOffer: null,
    }
  },
  methods: {
    async loadOfferDetail(id){
      try {
        this.selectedOffer = await this.offersService.asyncFindById(id);
        this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
        this.unsavedChanges = false;
      }catch (error){
        console.log("Error with loading the detail. " + error)
      }
    }
  },
  watch: {
    async '$route.params.id'() {
      if (this.$route.params.id){
        await this.loadOfferDetail(this.$route.params.id)
      }
    }
  },
  async created() {
    await this.loadOfferDetail(this.$route.params.id)
  },

  beforeRouteUpdate(to, from, next) {
    let cancelRouting = true

    if (this.hasChanged) {
      // Prompt the user for confirmation before leaving the route
      if (!window.confirm('Do you really want to leave this page?')) {
        // User confirmed, allow route leave
        cancelRouting = false
      }
    }
    next(cancelRouting);
  },

  beforeRouteLeave(to, from, next) {
    let cancelRouting = true

    if (this.hasChanged) {
      // Prompt the user for confirmation before leaving the route
      if (!window.confirm('Do you really want to leave this page?')) {
        // User confirmed, allow route leave
        cancelRouting = false
      }
    }
    next(cancelRouting);
  },

  mounted() {
    window.addEventListener('beforeunload', this.beforeWindowload);
  }, beforeUnmount() {
    window.addEventListener('beforeunload', this.beforeWindowload);
  }
};
</script>

<style scoped>
.inputfieldText {
  width: 97.5%;
}

.selectOptions {
  width: 40%;
}

.inputfieldDate {
  width: 97.5%;
  margin: 0 0 10px 0;
}

h2 {
  text-align: center;
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
</style>

