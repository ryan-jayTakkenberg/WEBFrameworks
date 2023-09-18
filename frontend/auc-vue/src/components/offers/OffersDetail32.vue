<template>
  <div >
    <div v-if="!selectedOffer">
      <h2>Select an offer from the left</h2>
    </div>
    <div v-if="selectedOffer">
      <table>
        <tbody>
        <tr>
          <th colspan="2">Offer details (id={{ selectedOffer.id }})</th>
        </tr>
        <tr>
          <th>Title:</th>
          <!--          eslint-disable vue/no-mutating-props-->
          <td><input type="text" class="inputfieldText" v-model="selectedOffer.title"></td>
        </tr>
        <tr>
          <th>Description:</th>
          <td><input type="text" class="inputfieldText"></td>
        </tr>
        <tr>
          <th>Status:</th>
          <td>
            <select class="selectOptions">
              <option> {{selectedOffer.status}}</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>Sell date:</th>
          <!-- eslint-disable vue/no-mutating-props-->
          <td><input type="date" class="inputfieldDate" v-model="selectedOffer.sellDate"><br>{{selectedOffer.sellDate}}</td>
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
export default {
  props: {
    selectedOffer: Object,
  },
  methods: {
    toggleSelection(offer) {
      // Emit an event to notify the parent component of the selection change
      this.$emit('toggle-selection', offer);
    },
    deleteDetails(){
      this.$emit('delete-offer', this.selectedOffer);
    }
  },
};
</script>

<style scoped>
.inputfieldText{
  width: 97.5%;
}

.selectOptions{
  width: 40%;
}

.inputfieldDate{
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

