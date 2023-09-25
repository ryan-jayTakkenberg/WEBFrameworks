import {createRouter, createWebHashHistory} from 'vue-router';

import WelcomePage from "@/components/WelcomeComponent.vue";
import OffersOverview31Component from "@/components/offers/OffersOverview31Component.vue";
import offersOverview32 from "@/components/offers/OffersOverview32.vue";
import unknownRoute from "@/components/UnknownRoute.vue";
import offersOverview33 from "@/components/offers/OffersOverview33";
import OffersDetail32 from "@/components/offers/OffersDetail32";

const routes = [
    { path: "/home", component: WelcomePage },
    { path: "/offers/overview31", component: OffersOverview31Component },
    { path: "/offers/overview32", component: offersOverview32},
    { path: "/offers/overview33", component: offersOverview33,
        children: [
            {
                path: ":id",
                component: OffersDetail32,
                props: true
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
