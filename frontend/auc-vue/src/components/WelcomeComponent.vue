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
      <h1>Auctions that expire today:</h1>

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



.column-middle {
  flex: 1; /* Dit zorgt ervoor dat de middelste kolom meeschaalt met het venster */
}

.column-left,
.column-right {
  width: 200px;
  height: auto;
  margin: 20px;
  /* Je kunt hier eventueel wat padding of marge toevoegen om de kolommen te scheiden */
}

.links {
  margin-bottom: 10px;
}


.main-image {
  flex: 1;
  width: 300px;
  margin: 10px;
}
</style>
