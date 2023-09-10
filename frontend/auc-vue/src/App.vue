<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
   <HeaderComponent/> <!-- Voeg de HeaderComponent hier toe -->
  <welcome/>

</template>

<script>
import HeaderComponent  from './components/HeaderComponent.vue'
import Welcome  from './components/Welcome.vue'

export default {
  name: 'App',
  components: {
    HeaderComponent,
    Welcome
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
