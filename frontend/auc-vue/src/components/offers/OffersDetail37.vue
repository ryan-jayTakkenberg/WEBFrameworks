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
          <th>Highest Bid:</th>
          <!-- eslint-disable vue/no-mutating-props -->
          <td><input type="text" class="inputfieldBid" v-model="copiedOffer.valueHighestBid"></td>
        </tr>
        </tbody>
      </table>
      <button @click="handleSaveOffer(this.copiedOffer)" :disabled="!hasChanged">Save</button>
      <button @click="handleClear">Clear</button>
      <button @click="handleReset" :disabled="!hasChanged">Reset</button>
      <button @click="handleCancel" :disabled="!hasChanged">Cancel</button>
      <button @click="handleDeleteOffer(this.copiedOffer)">Delete</button>


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
    handleClear(){
      this.copiedOffer.title = null;
      this.copiedOffer.description = null;
      this.copiedOffer.status = null;
      this.copiedOffer.sellDate = null;
      this.copiedOffer.valueHighestBid = null;
    },
    handleReset(){
      this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
    },
    handleCancel(){
      this.$router.push(this.$route.matched[0].path);
    },

    async handleDeleteOffer(offerToDelete) {
      try {
        await this.offersService.asyncDeleteById(offerToDelete.id);
        this.$router.push(this.$route.matched[0].path);
        this.$emit('update-offer-list')
      } catch (error) {
        console.log('Error while deleting an offer: ' + error);
      }

    },
    async handleSaveOffer(offer){
      try {
        await this.offersService.asyncSave(offer);
        this.$emit('update-offer-list')
      } catch (error) {
        console.log("Error while saving the offer: " + error);
      }
    },
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
  computed: {
    hasChanged() {
      return (
          this.selectedOffer.title !== this.copiedOffer.title ||
          this.selectedOffer.description !== this.copiedOffer.description ||
          this.selectedOffer.status !== this.copiedOffer.status ||
          this.selectedOffer.sellDate !== this.copiedOffer.sellDate ||
          this.selectedOffer.valueHighestBid !== this.copiedOffer.valueHighestBid
      )
    },

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
.confirmation-dialog {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.confirmation-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.confirmation-content p {
  margin-bottom: 10px;
  font-size: 16px;
}

.confirmation-content button {
  margin-left: 10px;
  width: 20%;
}

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

