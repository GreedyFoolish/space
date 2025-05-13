import {createRouter, createWebHistory} from "vue-router"
import Login from "@/views/Login.vue"

const routes = [
    {
        path: "/",
        redirect: "/login" // 根路径重定向到登录页
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
