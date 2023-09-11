<template>

    <HeaderComponent/>
  <NavBarComponent/>
    <WelcomeComponent/>

</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue'
import NavBarComponent from './components/NavBar.vue'
import WelcomeComponent from './components/Welcome.vue'


export default {
  name: 'App',
  components: {
    HeaderComponent,
    WelcomeComponent,
    NavBarComponent
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
  margin-top: 0px;
}
</style>
