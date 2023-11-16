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
                     v-model="offerDate">
            {{ formatDate(copiedOffer.sellDate) }}
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

      <div v-if="isConfirmationVisible" class="confirmation-dialog">
        <div class="confirmation-content">
          <p v-if="this.confirmAction === 'Delete' ">Are you sure you want to delete this action on id
            {{ this.selectedOffer.id }}</p>
          <p v-else>Are you sure you want to perform this action on id {{ this.selectedOffer.id }}</p>
          <button @click="confirm">Confirm</button>
          <button @click="cancel">Cancel</button>
        </div>
      </div>


    </div>
  </div>
</template>

<script>
import {Offer} from "@/models/offers";

export default {
  name: "offersDetail37",
  inject:  ['offersService'],
  props: ['offerList'],

  data() {
    return {
      offerStatusArray: Object.values(Offer.Status),
      selectedOffer: {
        title: '',
        description: '',
        status: '',
        sellDate: '',
        valueHighestBid: '',
      },
      copiedOffer: null,
      isConfirmationVisible: false,
      confirmAction: String,
      unsavedChanges: false,
    }
  },
  methods: {
    confirmation(action) {
      this.confirmAction = action;
      this.isConfirmationVisible = true;
    },
    cancel() {
      this.isConfirmationVisible = false;
    },
    formatDate(date) {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${day}-${month}-${year}`;
    },
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
      this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
      this.$router.push(this.$route.matched[0].path);
      this.$emit('Select-parent-class', this.selectedOffer);
    },
    async handleDeleteOffer(offerToDelete) {
      try {
        await this.offersService.asyncDeleteById(offerToDelete.id);
        this.$emit('update-offer-list')
      } catch (error) {
        console.log('Error while deleting an offer: ' + error);
      }

    },
    async handleSaveOffer(offer){
      try {
        await this.offersService.asyncSave(offer);
        this.unsavedChanges = false;
        this.$emit('update-offer-list')
      } catch (error) {
        console.log("Error while saving the offer: " + error);
      }
    },
    async loadOfferDetail(id){
      try {
        const offer = await this.offersService.asyncFindById(id);
        return offer;
      }catch (error){
        console.log("Error with loading the detail. " + error)
      }
    }
  },

  computed: {
    offerDate: {
      get() {
        if (this.copiedOffer.sellDate) {
          return this.formatDate(this.copiedOffer.sellDate);
        }
        return '';
      },
      set(localDate) {
        const parts = localDate.split('-');
        if (parts.length === 3) {
          const day = parseInt(parts[0], 10);
          const month = parseInt(parts[1], 10) - 1;
          const year = parseInt(parts[2], 10);
          this.copiedOffer.sellDate = new Date(year, month, day);
        }
      }
  },
    sellDateUpdater: {
      get() {
        if (this.copiedOffer && this.copiedOffer.sellDate) {
          return this.copiedOffer.sellDate;
        }
        return '';
      },
      set(localDateTime) {
        this.$emit('update-sell-date', localDateTime);
      },
    },
    hasChanged() {
      if (
          this.selectedOffer.title !== this.copiedOffer.title ||
          this.selectedOffer.description !== this.copiedOffer.description ||
          this.selectedOffer.status !== this.copiedOffer.status ||
          // this.selectedOffer.sellDate !== this.copiedOffer.sellDate ||
          this.selectedOffer.valueHighestBid !== this.copiedOffer.valueHighestBid
      ) {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.unsavedChanges = true;
        return true; // There are changes
      } else {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return false; // No changes
      }
    },
    isFormFilled() {
      if (this.selectedOffer) {
        return (
            this.selectedOffer.title &&
            this.selectedOffer.description &&
            this.selectedOffer.status &&
            this.selectedOffer.sellDate &&
            this.selectedOffer.valueHighestBid
        );
      } else {
        return false;
      }
    },
  },
  watch: {
    async '$route.params.id'() {
      this.selectedOffer = await this.loadOfferDetail(this.$route.params.id)
      this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
      this.unsavedChanges = false;
    }
  },
  created() {
    this.selectedOffer = this.offerList.find(offer => offer.id === parseInt(this.$route.params.id))
    this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
  },
  beforeRouteUpdate(to, from, next) {
    // Check if there are unsaved changes
    if (this.unsavedChanges) {
      // Prompt the user for confirmation
      if (window.confirm('You have unsaved changes. Do you really want to leave?')) {
        // User confirmed, allow route update if the form is filled
        if (this.isFormFilled) {
          this.unsavedChanges = false; // Reset the flag
          next();
        } else {
          // Display an alert and prevent route update
          alert('Please fill in all form fields before leaving.');
          next(false);
        }
      } else {
        // User canceled, stay on the current route
        next();
        this.unsavedChanges = false;
      }
    } else {
      // No unsaved changes, allow route update
      next();
    }
  },
  beforeRouteLeave(to, from, next) {
    if (this.unsavedChanges) {
      // Prompt the user for confirmation before leaving the route
      if (window.confirm('Do you really want to leave this page?')) {
        // User confirmed, allow route leave
        next();
      } else {
        // User canceled, stay on the current route
        next();
      }
    } else {
      // User canceled, stay on the current route
      next();
    }
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

