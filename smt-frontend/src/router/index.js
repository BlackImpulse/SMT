import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home'
import Signup from "@/components/Signup";
import NotFound from "@/components/NotFound";
import Login from "@/components/Login";

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home,
        },
        {
            path: '/sign-up',
            name: 'Signup',
            component: Signup
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '*',
            name: 'Not Found',
            component: NotFound
        }
    ],
});
