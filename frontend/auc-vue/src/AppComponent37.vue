<template>
  <HeaderComponent/>
  <NavBarComponent/>

  <router-view></router-view>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import NavBarComponent from "@/components/NavBarComponent.vue";
import CONFIG from "@/app-config"
import {OffersAdaptor} from "@/service/offers-adaptor";


export default {
  name: 'AppComponent37',
  components: {
    HeaderComponent,
    NavBarComponent,
  },
  provide(){
    return{
      offersService: new OffersAdaptor(CONFIG.BACKEND_URL+"/offers")
    }
  }
}
</script>

<style scoped>
/* Add your scoped styles here */
</style>
