<template>
  <HeaderSbComponent/>
  <NavBarSbComponent/>

  <router-view></router-view>
</template>

<script>
import HeaderSbComponent from "@/components/HeaderSbComponent";
import CONFIG from "@/app-config"
import {OffersAdaptor} from "@/service/offers-adaptor";
import {shallowReactive} from "vue";
import {SessionSbService} from "@/service/Session-Sb-Service";
import NavBarSbComponent from "@/components/NavBarSb";
import {FetchInterceptor} from "@/service/FetchInterceptor";


export default {
  name: 'AppComponent44',
  components: {
    NavBarSbComponent,
    HeaderSbComponent,
  },
  provide(){



    this.theSessionService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + "/authentication", CONFIG.JWT_STORAGE_ITEM));

    this.theFetchInterceptor =
        new FetchInterceptor(this.theSessionService, this.$router);
    return{
      offersService: new OffersAdaptor(CONFIG.BACKEND_URL+"/offers"),
      sessionService: this.theSessionService
    }
  },
  unmounted() {
    console.log("App.unmounted() has been called")
    this.theFetchInterceptor.unregister();
  }
}
</script>

<style scoped>
/* Add your scoped styles here */
</style>