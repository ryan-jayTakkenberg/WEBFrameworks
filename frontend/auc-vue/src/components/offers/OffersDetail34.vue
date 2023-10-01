<template>
  <div>
    <div v-if="!selectedOffer">
      <h2>Select an offer from the left</h2>
    </div>
    <div v-else>
      <table>
        <tbody>
        <tr>
          <th  colspan="2">Offer details (id={{ copiedOffer.id }})</th>
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

          <td><input type="datetime-local" class="inputfieldDate"
                     v-model="sellDateUpdater"><br>{{ selectedOffer.sellDate }}
          </td>
        </tr>
        <tr>
          <th>Highest Bid:</th>
          <!-- eslint-disable vue/no-mutating-props -->
          <td><input type="text" class="inputfieldBid" v-model="copiedOffer.valueHighestBid"></td>
        </tr>
        </tbody>
      </table>
      <button @click="handleSave" :disabled="!hasChanged">Save</button>
      <button @click="handleCancel" :disabled="hasChanged">Cancel</button>
      <button @click="handleReset" :disabled="!hasChanged" >Reset</button>
      <button @click="handleClear">Clear</button>
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
    },
    handleSave(){
      // TODO oplossing vinden voor de date

      this.selectedOffer.title = this.copiedOffer.title;
      this.selectedOffer.description = this.copiedOffer.description;
      this.selectedOffer.status =  this.copiedOffer.status;
      this.selectedOffer.sellDate = this.copiedOffer.sellDate;
      this.selectedOffer.valueHighestBid = this.copiedOffer.valueHighestBid;

    },
    handleCancel(){

      this.copiedOffer.title = this.selectedOffer.title;
      this.copiedOffer.description = this.selectedOffer.description;
      this.copiedOffer.status = this.selectedOffer.status;
      this.copiedOffer.sellDate = this.selectedOffer.sellDate;
      this.copiedOffer.valueHighestBid = this.selectedOffer.valueHighestBid;
      this.$router.push(this.$route.matched[0].path);
      this.$emit('Select-parent-class', this.selectedOffer);
    },
    handleClear(){
      this.copiedOffer.title = null;
      this.copiedOffer.description = null;
      this.copiedOffer.status = null;
      this.copiedOffer.sellDate = null;
      this.copiedOffer.valueHighestBid = null;
    },
    handleReset(){
      this.copiedOffer.title = this.selectedOffer.title;
      this.copiedOffer.description = this.selectedOffer.description;
      this.copiedOffer.status = this.selectedOffer.status;
      this.copiedOffer.sellDate = this.selectedOffer.sellDate;
      this.copiedOffer.valueHighestBid = this.selectedOffer.valueHighestBid;
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
          return dateObject.toLocaleDateString("de-DE", Offer.DateFormat);
        }
        return '';
      },
      set(localDateTime) {
        const date = new Date(localDateTime);
        this.$emit('update-sell-date', date.toLocaleDateString("en-US", Offer.DateFormat));
      },

    }, hasChanged() {
      return this.selectedOffer.title !== this.copiedOffer.title || this.selectedOffer.description !== this.copiedOffer.description
          || this.selectedOffer.status !== this.copiedOffer.status || this.selectedOffer.sellDate !== this.copiedOffer.sellDate || this.selectedOffer.valueHighestBid !== this.copiedOffer.valueHighestBid;
    },
  },
      watch: {
        '$route.params.id'() {
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
  margin: 5px 0 0 5px;
  height: 35px;
  width: 13%;
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

