import {createRouter, createWebHashHistory} from 'vue-router';

import WelcomePage from "@/components/WelcomeComponent.vue";
import OffersOverview31 from "@/components/offers/OffersOverview31.vue";
import offersOverview32 from "@/components/offers/OffersOverview32.vue";
import unknownRoute from "@/components/UnknownRoute.vue";
import offersOverview33 from "@/components/offers/OffersOverview33";
import offersOverview34 from "@/components/offers/OffersOverview34.vue";
import OffersDetail33 from "@/components/offers/OffersDetail33.vue";
import OffersDetail34 from "@/components/offers/OffersDetail34";
import OffersDetail37 from "@/components/offers/OffersDetail37.vue";
import offersOverview37 from "@/components/offers/OffersOverview37.vue";
import logInComponent from "@/components/LogInComponent";

const routes = [
    { path: "/", component: WelcomePage },
    { path: "/offers/overview31", component: OffersOverview31},
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
        ]},{ path: "/offers/overview37", component: offersOverview37,
        children: [
            {
                path: ":id",
                component: OffersDetail37,
            }
        ]},
    { path: "/sign-in", component: logInComponent},
    { path: "/sign-out", redirect: "/sign-in", query: {signOff: true}},
    { path: "/:catchAll(.*)", component: unknownRoute }
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes
});
