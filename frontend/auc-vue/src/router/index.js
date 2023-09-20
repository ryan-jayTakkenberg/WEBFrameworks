import {createRouter, createWebHashHistory} from 'vue-router';

import WelcomePage from "@/components/WelcomeComponent.vue";
import OffersOverview31Component from "@/components/offers/OffersOverview31Component.vue";
import offersOverview32 from "@/components/offers/OffersOverview32.vue";
import unknownRoute from "@/components/UnknownRoute.vue";
import offersOverview33 from "@/components/offers/OffersOverview33";
import OffersDetail32 from "@/components/offers/OffersDetail32";

const routes = [
    { path: "/", component: WelcomePage },
    { path: "/offers", component: OffersOverview31Component },
    { path: "/offeractive", },
    { path: "/offerhistory", },
    { path: "/myaccount", },
    { path: "/login", },
    { path: "/signup", },
    { path: "/:catchAll(.*)", component: unknownRoute },
    { path: "/offersEdit", component: offersOverview33,
    children: [
        {
            path: ":id",
            component: OffersDetail32
        }
    ]},
    { path: "/offersDetail", component: offersOverview32}
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes
});
