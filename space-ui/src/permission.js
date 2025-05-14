import router from "./router"
import NProgress from "nprogress"
import "nprogress/nprogress.css"
import {getToken} from "@/utils/auth"
import {isPathMatch} from "@/utils/validate"
import {usePermissionStore} from "@/stores/permissionStore.js"

// 白名单，无需登录即可访问的路径
const whiteList = ["/login", "/register"]
// 进度条配置
NProgress.configure({showSpinner: false})

const isWhiteList = (path) => {
    return whiteList.some(pattern => isPathMatch(pattern, path))
}
router.beforeEach((to, from, next) => {
    const userToken = getToken()
    NProgress.start()

    if (userToken) {
        if (to.path === "/login") {
            next({path: "/home"})
            NProgress.done()
        } else if (isWhiteList(to.path)) {
            // 在免登录白名单，直接进入
            next()
        } else {
            // usePermissionStore().generateRouteList().then(accessRoutes => {
            //     // 动态添加可访问路由表
            //     accessRoutes.forEach(route => router.addRoute(route))
            //     // 解决路由重复跳转的问题
            //     next({...to, replace: true})
            // })
            next()
        }
    } else {
        // 没有 token
        if (isWhiteList(to.path)) {
            // 在免登录白名单，直接进入
            next()
        } else {
            // 其他页面跳转登录页
            next(`/login?redirect=${encodeURIComponent(to.fullPath)}`)
            NProgress.done()
        }
    }
})

router.afterEach(() => {
    NProgress.done()
})

export default router
