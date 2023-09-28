<template>
  <div>
    <div v-if="!selectedOffer">
      <h2>Select an offer from the left</h2>
    </div>
    <div v-else>
      <table>
        <tbody>
        <tr>
          <th  colspan="2">Offer details (id={{ selectedOffer.id }})</th>
        </tr>
        <tr>
          <th>Title:</th>
          <!-- eslint-disable vue/no-mutating-props-->
          <td><input type="text" class="inputfieldText" v-model="selectedOffer.title"></td>
        </tr>
        <tr>
          <th>Description:</th>
          <td><input type="text" class="inputfieldText" v-model="selectedOffer.description"></td>
        </tr>
        <tr>
          <th>Status:</th>
          <td>
            <select class="selectOptions" v-model="selectedOffer.status">
              <option v-for="status in offerStatusArray" :key="status" :value="status">{{status}}</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>Sell date:</th>
          <!-- eslint-disable vue/no-mutating-props-->
          <td><input type="datetime-local" class="inputfieldDate"
                     v-model="sellDateUpdater"><br>{{ selectedOffer.sellDate }}
          </td>
        </tr>
        <tr>
          <th>Highest Bid:</th>
          <!-- eslint-disable vue/no-mutating-props -->
          <td><input type="text" class="inputfieldBid" v-model="selectedOffer.valueHighestBid"></td>
        </tr>
        </tbody>
      </table>
      <button @click="deleteDetails">Delete</button>

    </div>
  </div>
</template>

<script>
import {Offer} from "@/models/offers";

export default {
  props: ['offerList'],

  data() {
    return {
      offerStatusArray: Object.values(Offer.Status),
      selectedOffer: null,
      copiedOffer: null,
    }
  },
  methods: {
    deleteDetails() {
      this.$emit('delete-offer', this.selectedOffer);
    }
  },
  created(){
    this.selectedOffer = this.offerList.find(offer => offer.id === parseInt(this.$route.params.id));
    this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
  },
  computed: {
    sellDateUpdater: {
      get() {
        if (this.selectedOffer && this.selectedOffer.sellDate) {
          const dateObject = new Date(this.selectedOffer.sellDate);
          const format = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'}
          // const year = dateObject.getFullYear();
          // const month = (dateObject.getMonth() + 1).toString().padStart(2, "0");
          // const day = dateObject.getDate().toString().padStart(2, "0");
          // const hours = dateObject.getHours().toString().padStart(2, "0");
          // const minutes = dateObject.getMinutes().toString().padStart(2, "0");
          //
          // return `${year}-${month}-${day} ${hours}:${minutes}`;
          return dateObject.toLocaleDateString("de-DE", format);
        }
        return '';
      },
      set(localDateTime) {
        const date = new Date(localDateTime);
        const format = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'}

        this.$emit('update-sell-date', date.toLocaleDateString("en-US", format));
      }
    }
  },
  watch: {
    '$route.params.id'(){
      this.selectedOffer = this.offerList.find(offer => offer.id === parseInt(this.$route.params.id));
      this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
    }
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

button {
  margin: 10px;
  height: 35px;
  width: 200px;
  background-color: pink;
  position: relative;
  float: right;
  border: none;
  font-size: 16px;
}

button:hover {
  background-color: sandybrown;
  cursor: pointer;
}
</style>

