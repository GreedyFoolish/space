import NProgress from "nprogress"
import "nprogress/nprogress.css"
import { usePermissionStore } from "@/stores/permissionStore.js"
import { getToken } from "@/utils/auth"
import { isPathMatch } from "@/utils/validate"
import router from "./router"

// 白名单，无需登录即可访问的路径
const whiteList = ["/login", "/register"]
// 进度条配置
NProgress.configure({ showSpinner: false })

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
            if (userStore.roles.length === 0 || usePermissionStore().routes.length < 1) {
                usePermissionStore().generateRouteList().then(accessRoutes => {
                    // 解决路由重复跳转的问题
                    next({ ...to, replace: true })
                })
            } else {
                next()
            }
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
