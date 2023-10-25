<template>
  <HeaderComponent/>
  <NavBarComponent/>
  <router-view></router-view>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import NavBarComponent from "@/components/NavBarComponent.vue";


export default {
  name: 'AppComponent37',
  components: {
    HeaderComponent,
    NavBarComponent,
  },
}
</script>

<style scoped>
/* Add your scoped styles here */
</style>
