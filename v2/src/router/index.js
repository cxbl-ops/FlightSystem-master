import login from '@/views/User/login.vue'
import home from '@/views/home.vue'
import register from '@/views/User/register.vue'
import user from '@/views/user.vue'
import FlightInfo from "@/views/Flight/FlightInfo.vue";


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
  // {
  //   name: 'home',
  //   path: '/home',
  //   component: home,
  // },
  {
    name:'FlightInfo',
    path:'/FlightInfo',
    component: FlightInfo,
  },

  {
    name: "user",
    path: '/user',
    component: user,
  },

  // {
  //   name: "index",
  //   path: "/index",
  //   component: index,
  // },
  // {
  //   path: '/flight-info',
  //   name: 'FlightInfo',
  //   component: FlightInfo
  // },
  // {
  //   path: '/user-management',
  //   name: 'UserManagement',
  //   component: UserManagement
  // }
]

export default routes