<template>
  <div>
    <div>
      <h2>Offer Details</h2>
    </div>
    <div v-if="!selectedOffer">
      <h2>Select one offer from above</h2>
    </div>
    <div v-if="selectedOffer">
      <table>
        <tbody>
        <tr>
          <th colspan="2">ID: {{ selectedOffer.id }}</th>
        </tr>
        <tr>
          <th>Title:</th>
          <td>{{ selectedOffer.title }}</td>
        </tr>
        </tbody>
      </table>
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
  },
};
</script>


<style scoped>
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
.full-width {
  width: 100%;
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

