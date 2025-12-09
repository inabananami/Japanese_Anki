import {createRouter, createWebHistory} from 'vue-router'

import Home from '../components/Home.vue'
import Word from '../components/word/WordHome.vue'
import Search from '../components/word/SearchWord.vue'
import Login from '../components/auth/Login.vue'
import List from '../components/word/ListWord.vue'
import SignUp from '../components/auth/SignUp.vue'
import User from '../components/user/User.vue'
import UserInfo from '../components/user/UserInfo.vue'
import UserSecurity from '../components/user/UserSecurity.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/sign-up',
            component: SignUp
        },
        {
            path: '/word',
            component: Word,
        },
        {
            path: '/word/list',
            component: List
        },
        {
            path: '/word/search',
            component: Search
        },
        {
            path: '/user',
            component: User
        },
        {
            path: '/user/info',
            component: UserInfo
        },
        {
            path: '/user/security',
            component: UserSecurity
        }
    ]
})

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')

    //如果有token，访问login时自动跳到home
    if(token && to.path === '/login') {
        next('/home')
        return
    }

    //没有token时，访问除login和sign-up以外的页面，跳到login
    if(!token && to.path !== '/login' && to.path !== '/sign-up') {
        next('/login')
        return
    }

    next()
})
export default router