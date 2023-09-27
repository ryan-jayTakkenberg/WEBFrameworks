import {createRouter, createWebHashHistory} from 'vue-router';

import WelcomePage from "@/components/WelcomeComponent.vue";
import OffersOverview31Component from "@/components/offers/OffersOverview31Component.vue";
import offersOverview32 from "@/components/offers/OffersOverview32.vue";
import unknownRoute from "@/components/UnknownRoute.vue";
import offersOverview33 from "@/components/offers/OffersOverview33";
import OffersDetail32 from "@/components/offers/OffersDetail32";
import offersOverview34 from "@/components/offers/OffersOverview34.vue";
import OffersDetail33 from "@/components/offers/OffersDetail33.vue";
import OffersDetail34 from "@/components/offers/OffersDetail34";

const routes = [
    { path: "/", component: WelcomePage },
    { path: "/offers/overview31", component: OffersOverview31Component },
    { path: "/offers/overview32", component: offersOverview32},
    { path: "/offers/overview33", component: offersOverview33,
        children: [
            {
                path: ":id",
                component: OffersDetail33,
            }
        ]},{ path: "/offers/overview34", component: offersOverview34,
        children: [
            {
                path: ":id",
                component: OffersDetail34,
            }
        ]},
    { path: "/offer/active", },
    { path: "/offer/history", },
    { path: "/sign-in", },
    { path: "/sign-up", },
    { path: "/myaccount", },
    { path: "/:catchAll(.*)", component: unknownRoute }
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes
});
