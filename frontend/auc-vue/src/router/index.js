import {createRouter, createWebHashHistory} from 'vue-router';
import WelcomePage from "@/components/WelcomeComponent.vue";
import OffersOverview31Component from "@/components/offers/OffersOverview31Component.vue";

import offersOverview32 from "@/components/offers/OffersOverview32.vue";

export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/",
            component: WelcomePage,
        },
        {
            path: "/offers",
            component: OffersOverview31Component,
        },
        {
            path: "/offeractive",
            component: offersOverview32,
        },
        {
            path: "/offerhistory",

        },
        {
            path: "/myaccount",

        }, {
            path: "/login",
        },
        {
            path: "/signup",

        },

    ]
});
