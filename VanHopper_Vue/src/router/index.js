import { createWebHashHistory, createRouter} from "vue-router";
import CreateUser from '../components/CreateUser.vue';
import UserLogin from '../components/UserLogin.vue';
import UserPage from '../components/UserPage.vue';
import CreateEvent from '../components/CreateEvent.vue';
import AdminPage from '../components/AdminPage.vue';
import EventsList from '../components/EventsList.vue';
import SearchEvents from '../components/SearchEvents.vue';
import UsersList from '../components/UsersList.vue';
import AEventsPractice from '../components/AEventsPractice.vue';
import EventCart from '../components/EventCart.vue';
const routes=[
    {
        path:"/",
        alias:"/login",
        name:"userLogin",
        component:UserLogin
    },
    {
        path:"/users",
        name:"createUser",
        component:CreateUser
    },
    {
        path:"/usersPage",
        name:"userPage",
        component:UserPage
    },
    {
        path:"/events",
        name:"createEvent",
        component:CreateEvent
    },
    {
        path:"/admin",
        name:"adminPage",
        component:AdminPage
    },
    {
        path:"/eventsList",
        name:"eventsList",
        component:EventsList
    },
    {
        path:"/searchEvents",
        name:"searchEvents",
        component:SearchEvents
    },
    {
        path:"/usersList",
        name:"usersList",
        component:UsersList
    },
    {
        path:"/eventsListTest",
        name:"eventsListTest",
        component:AEventsPractice
    },
    {
        path:"/eventCart",
        name:"eventCart",
        component:EventCart
    },
];

const router = createRouter({
    history:createWebHashHistory(),
    routes
})

export default router;