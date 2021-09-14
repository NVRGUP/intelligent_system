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
    }

]
const router =new VueRouter(
    {
        routes
    }
)
export default router