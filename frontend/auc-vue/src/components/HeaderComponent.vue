<template>
  <header class="header">
    <div class="header-content">
      <div class="left-image">
        <img src="@/assets/tijd.jpg" alt="Tijd afbeelding" class="header-image">
      </div>
      <div class="text-content">
        <h1>Header werkt!</h1>
        <p class="subtitle">Offered to you by Hva.nl </p>
      </div>
      <div class="right-image">
        <img src="@/assets/veilinghamer.jpg" alt="Veilinghamer afbeelding" class="header-image">
      </div>
    </div>
  </header>
</template>

<style scoped>
.header {
  padding: 10px;
  border-bottom: 1px solid black;
  background-color: pink;

}

.header-content {

  display: flex;
  align-items: center;
}


h1 {
  border-bottom: 3px solid white;
  margin: 0;
  padding-bottom: 20px;
}
.header-image{
  height: 125px;


}
.left-image,
.right-image {
  display: flex;
  align-items: center;
padding: 5px;


}
.right-image{
  border-left: 3px solid white;
}

.left-image{
  border-right: 3px solid white;
}

.text-content {
  flex: 2; /* Let the text content take up more space than images */
  text-align: center; /* Center-align text content */

}

.subtitle {
  text-align: right; /* Right-align subtitle text */
  padding-right: 15px;
}


</style>

<script setup>
</script>
