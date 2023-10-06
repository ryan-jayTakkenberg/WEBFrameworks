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
                     v-model="offerDate"><br>{{ copiedOffer.sellDate }}
          </td>
        </tr>
        <tr>
          <th>Highest Bid:</th>
          <!-- eslint-disable vue/no-mutating-props -->
          <td><input type="text" class="inputfieldBid" v-model="copiedOffer.valueHighestBid"></td>
        </tr>
        </tbody>
      </table>
      <button @click="confirmation('Save')" :disabled="!hasChanged">Save</button>
      <button @click="confirmation('Clear')">Clear</button>
      <button @click="confirmation('Reset')" :disabled="!hasChanged">Reset</button>
      <button @click="confirmation('Cancel')" :disabled="!hasChanged">Cancel</button>
      <button @click="confirmation('Delete')">Delete</button>

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
  props: ['offerList'],

  data() {
    return {
      offerStatusArray: Object.values(Offer.Status),
      selectedOffer: { // Your selected offer object
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
    confirm() {
      if (this.confirmAction === "Clear") {
        this.copiedOffer.title = null;
        this.copiedOffer.description = null;
        this.copiedOffer.status = null;
        this.copiedOffer.sellDate = null;
        this.copiedOffer.valueHighestBid = null;
      } else if (this.confirmAction === "Reset") {
        this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
      } else if (this.confirmAction === "Cancel") {
        this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
        this.$router.push(this.$route.matched[0].path);
        this.$emit('Select-parent-class', this.selectedOffer);
      } else if (this.confirmAction === "Save") {
        Object.assign(this.selectedOffer, this.copiedOffer)
        console.log(this.copiedOffer)
        console.log(this.selectedOffer)
      } else if (this.confirmAction === "Delete") {
        this.$emit('delete-offer', this.selectedOffer);
      }
      this.isConfirmationVisible = false;
      this.unsavedChanges = false;
    },
    beforeWindowload(event) {
      // Display a custom confirmation message
      event.returnValue = 'You have unsaved changes. Are you sure you want to leave?';
    },
  },
  computed: {
    offerDate: {
      get()
      {
        return this.copiedOffer.sellDate.toString()
      },
      set(localDate){
        this.copiedOffer.sellDate = new Date(localDate)
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
    '$route.params.id'() {
      this.selectedOffer = this.offerList.find(offer => offer.id === parseInt(this.$route.params.id));
      this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
      this.unsavedChanges = false;
    }
  },
  created() {
    this.selectedOffer = this.offerList.find(offer => offer.id === parseInt(this.$route.params.id))
    this.copiedOffer = Offer.copyConstructor(this.selectedOffer);
  }, beforeRouteUpdate(to, from, next) {
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
  }, mounted() {
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

