import {createRouter, createWebHistory} from "vue-router"

// 公共路由
export const constantRoutes = [
    {
        path: "/",
        redirect: "/login"
    },
    {
        path: "/login",
        component: () => import("@/views/Login.vue"),
        hidden: true
    },
    {
        path: "/register",
        component: () => import("@/views/Register.vue"),
        hidden: true
    },
    {
        path: "/home",
        component: () => import("@/views/Home.vue"),
        hidden: true
    },
    {
        path: "/404",
        component: () => import("@/views/404.vue"),
        hidden: true
    }
]

// 动态路由
export const dynamicRoutes = []

// 创建路由实例
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRoutes,
    scrollBehavior: () => ({y: 0})
})

const originalPush = router.push
const originalReplace = router.replace

// 重写push方法，解决push重复点击报错的问题
router.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

// 重写replace方法，解决replace重复点击报错的问题
router.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err)
}

export default router
