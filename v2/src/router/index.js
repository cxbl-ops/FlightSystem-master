import login from '@/views/User/login.vue'
import register from '@/views/User/register.vue'
import FlightInfo from "@/views/Flight/FlightInfo.vue";
import UserInfo from "@/views/User/UserInfo.vue";
import index from "@/views/index.vue";

const routes = [
  {
    name: 'login',
    path: '/login',
    component: login
  },
  {
    name: 'register',
    path: '/register',
    component: register
  },
  {
    path: "/",
    redirect: "/login",
  },
  {
    name: "index",
    path: "/index",

    children: [
      {

        path: "FlightInfo",
        component: FlightInfo,
      },
      {

        path: "userInfo",
        component: UserInfo
      }
    ],
    component: index
  }
]

export default routes
