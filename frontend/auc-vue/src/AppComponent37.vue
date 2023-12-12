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
import {reactive, shallowReactive} from "vue";
import {SessionSbService} from "@/service/Session-Sb-Service";
import {Offer} from "@/models/offers";


export default {
  name: 'AppComponent37',
  components: {
    HeaderComponent,
    NavBarComponent,
  },
  provide(){
    this.theSessionService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + "/authentication", CONFIG.JWT_STORAGE_ITEM));
    return{
      cachedOffersService: reactive(
          new CachedRestAdoptorWithFetch(CONFIG.BACKEND_URL+"/offers", Offer.copyConstructor()),
          sessionService: this.theSessionService;
      offersService: new OffersAdaptor(CONFIG.BACKEND_URL+"/offers")
    }
  }
}
</script>

<style scoped>
/* Add your scoped styles here */
</style>
