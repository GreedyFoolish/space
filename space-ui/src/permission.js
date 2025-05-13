import router from "./router"
import {getToken} from "@/utils/auth"
import {isPathMatch} from "@/utils/validate"

// 白名单，无需登录即可访问的路径
const whiteList = ["/login", "/register"]

const isWhiteList = (path) => {
    return whiteList.some(pattern => isPathMatch(pattern, path))
}
router.beforeEach((to, from, next) => {
    const hasToken = getToken()

    if (hasToken) {
        if (to.path === "/login") {
            next({path: "/"})
        } else if (isWhiteList(to.path)) {
            // 在免登录白名单，直接进入
            next()
        } else {
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
        }
    }
})

export default router
