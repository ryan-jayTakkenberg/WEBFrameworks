<template>
  <img alt="Vue logo" src="./assets/logo.png">
<!--  <HelloWorld msg="Welcome to Your Vue.js App"/>-->
</template>

<script>
import HeaderComponent  from './components/HeaderComponent.vue'
import WelcomeComponent  from './components/WelcomeComponent.vue'
import NavBarComponent from "@/components/NavBarComponent";

export default {
  name: 'App',
  components: {
    HeaderComponent,
    NavBarComponent,
    WelcomeComponent
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
