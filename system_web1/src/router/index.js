import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path:'/',
        name:'Home',
        redirect:'/login',
        component:Home
    },
    {
        path:'/login',
        name:'Login',
        component:()=>import('../components/Login.vue')
    },
    {
        path:'/home',
        name:'Home',
        component: Home
    }

]
const router =new VueRouter(
    {
        mode:'history',
        routes
    }
)
export default router