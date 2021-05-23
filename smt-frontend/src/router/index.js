import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home'
import Signup from "@/components/Signup";
import NotFound from "@/components/NotFound";
import Login from "@/components/Login";
import Spotify from "@/components/Spotify";
import Youtube from "@/components/Youtube";
import UserProfile from "@/components/UserProfile";

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
            path: '/spotify',
            component: Spotify,
            props: route => ({code: route.query.code})
        },
        {
            path: '/youtube',
            component: Youtube,
            props: route => ({code: route.query.code})
        },
        {
            path: '/profile',
            name: "profile",
            component: UserProfile
        },
        {
            path: '*',
            name: 'Not Found',
            component: NotFound
        }
    ],
});
