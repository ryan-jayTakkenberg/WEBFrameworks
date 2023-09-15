<script>
export default {
  name: 'WelcomeComponent',
}

</script>
<template>
  <div class="main-content">
    <div class="column-left">
      <div>
        <h1>Pick a date</h1>
        <input type="date">
        <h2>The categories</h2>

        <ul>
          <li class="links">
            <a href="google.com">link text</a>
          </li>
          <li class="links">
            <a href="google.com">link text</a>
          </li>
          <li class="links">
            <a href="google.com">link text</a>
          </li>
          <li class="links">
            <a href="google.com">link text</a>
          </li>
        </ul>
      </div>
    </div>

    <div class="column-middle">
      <h1 class="h1-column-middle">Auctions that expire today:</h1>

      <a href="https://google.com" >
        <img  src="@/assets/veilinghamer.jpg" alt="Veilinghamer afbeelding" class="main-image">
      </a>
      <a href="https://google.com" >
        <img  src="@/assets/veilinghamer.jpg" alt="Veilinghamer afbeelding" class="main-image">
      </a>
      <a href="https://google.com" >
        <img  src="@/assets/veilinghamer.jpg" alt="Veilinghamer afbeelding" class="main-image">
      </a>
      <a href="https://google.com" >
        <img  src="@/assets/veilinghamer.jpg" alt="Veilinghamer afbeelding" class="main-image">
      </a>



    </div>

    <div class="column-right">
      <h1>Original Race Truck</h1>

      <p>Truckracing is het racen met vrachtwagentrekkers zonder oplegger.
        De trucks worden speciaal gebouwd of geprepareerd voor het racen. Bekende merken die meedoen zijn MAN, Mercedes-Benz, Freightliner, Renault, Foden, Iveco, DAF en Volvo.

        <strong> Een truck heeft een motor van meer dan 1000 pk, die voorzien is van 1 of meer turbo's.</strong>
        De maximumsnelheid, die gereden mag worden, is 160 km per uur. Er zijn sinds 1994 twee klassen: Superracetrucks (SRT) en Racetrucks (RT).</p>
    </div>
  </div>
</template>

<style scoped>
.main-content {
  display: flex;
  flex-direction: row;
}

.h1-column-middle{
  text-align: center;
}

input{
  text-align: center;
  margin: 0px 15px 0px 15px;
  border-radius: 5px;
  border-color: white;
  height: 20px;
  width: 150px;
}

.column-middle {
  flex: 1; /* Dit zorgt ervoor dat de middelste kolom meeschaalt met het venster */
}

.column-left,
.column-right {
  height: auto;
  padding: 5px;
  margin-top: 5px;
  /* Je kunt hier eventueel wat padding of marge toevoegen om de kolommen te scheiden */
}

.column-right{
  width: 400px;
  margin-left: 20px;
  background-color: pink;
}

.column-left{
  width: 200px;
  margin-right: 20px;
  background-color: pink;

}

.links {
  margin-bottom: 10px;
}


.main-image {
  max-width: 100%;
  height: auto;
  margin: 10px;
  flex-grow: 1;
}
</style>
