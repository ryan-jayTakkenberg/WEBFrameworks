<template>
  <header class="header">
    <div class="header-content">
      <div class="left-image">
        <img src="@/assets/tijd.jpg" alt="Tijd afbeelding" class="header-image">
      </div>
      <div class="text-content">
        <h1>The Auctioneer</h1>
        <div class="HeaderSplit">
          <div class="row">
            <div class="col" id = "col-left">
              <p class="date">Date</p>
            </div>
            <div class="col">
              <p class="subtitle">Offered to you by Hva.nl</p>
            </div>
          </div>
        </div>
      </div>
      <div class="right-image">
        <img  src="@/assets/veilinghamer.jpg" alt="Veilinghamer afbeelding" class="header-image">
      </div>
    </div>
  </header>
</template>

<style scoped>
/* Style voor de "row" */
.HeaderSplit .row {
  display: flex;
  align-items: center;
}



.row {
padding-bottom: 0;

}

/* Style voor de "col" binnen de "row" */
.HeaderSplit .col {
 flex: 1;
}

/* Optioneel: Voeg ruimte tussen de "col" elementen toe */
.HeaderSplit .col + .col {
  margin-left: 20px; /* Voeg de gewenste ruimte tussen de kolommen toe */
}

.header {

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

  max-width: 100%;
  height: auto;


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
.date{
  text-align: left;
  padding-left: 15px;
}



</style>

<script>
export default {
  name: 'HeaderComponent',
}


</script>
