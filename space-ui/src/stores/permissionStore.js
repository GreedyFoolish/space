import { defineStore } from "pinia"
import { getRouterList } from "@/api/system/router.js"
import InnerLink from "@/layout/InnerLink.vue"
import ParentView from "@/layout/ParentView.vue"
import Layout from "@/layout/index.vue"
import router, { constantRoutes, dynamicRoutes } from "@/router"

// 获取所有 views 下的 vue 文件
const views = import.meta.glob("@/views/**/*.vue")

const componentMap = {
    Layout,
    ParentView,
    InnerLink
}

// 加载路由组件
const loadView = (componentName) => {
    const path = `/src/views/${componentName}`
    const key = Object.keys(views).find(key => key === path)

    if (key) {
        return views[key]
    } else {
        console.warn(`未找到组件：${componentName}`)
        return () => import("@/views/404.vue")
    }
}

export const usePermissionStore = defineStore("permission", {
    state: () => ({
        routes: [],
        topNavbarRoutes: []
    }),
    actions: {
        generateRouteList() {
            return new Promise(resolve => {
                try {
                    getRouterList().then(res => {
                        const routeList = JSON.parse(JSON.stringify(res.data))
                        // 拼接子路由
                        const concatenateRoutes = filterAsyncRouter(res.data, true)
                        // 过滤侧边栏路由
                        const topNavbarRoutes = filterAsyncRouter(routeList)
                        // 过滤动态路由
                        const asyncRoutes = filterDynamicRoutes(dynamicRoutes)
                        // 添加 404 路由
                        concatenateRoutes.push({ path: "/:pathMatch(.*)*", redirect: "/404", hidden: true })
                        // 添加动态路由
                        asyncRoutes.forEach(route => router.addRoute(route))
                        // 添加拼接后的路由
                        concatenateRoutes.forEach(route => router.addRoute(route))
                        // 设置最终路由
                        this.routes = constantRoutes.concat(concatenateRoutes)
                        this.topNavbarRoutes = topNavbarRoutes
                        resolve(this.routes)
                    })
                } catch (error) {
                    console.error("生成路由失败:", error)
                }
            })
        }
    }
})

// 递归过滤异步路由表，返回符合用户角色权限的路由表
function filterAsyncRouter(asyncRouterMap, concatenate = false) {
    return asyncRouterMap.filter(route => {
        if (!checkPermission(route)) {
            return false
        }
        // 路由路径替换
        route.path = route.navUrl
        // 路由名称
        route.name = route.navName
        // 固定路由
        if (route.navSort === 0) {
            route.affix = true
        }
        // 组件映射
        const component = route.navComponent
        if (component) {
            if (componentMap[component]) {
                route.component = componentMap[component]
            } else {
                route.component = loadView(component)
            }
        }
        // 递归过滤子路由
        if (route.children?.length > 0) {
            // 拼接子路由
            if (concatenate) {
                route.children = filterChildren(route.children, route)
            }
            route.children = filterAsyncRouter(route.children, concatenate)
        } else {
            delete route["children"]
            delete route["redirect"]
        }

        return route
    })
}

// 权限校验函数
function checkPermission(route) {
    return true
}

// 子路由路径拼接
function filterChildren(childrenMap, parentRouter) {
    const children = []
    childrenMap.forEach(item => {
        // 路径去重
        item.navUrl = normalizePath(item.navUrl)
        if (parentRouter) {
            item.navUrl = parentRouter.navUrl + "/" + item.navUrl
            // 组件路径
            item.path = item.navUrl
            // 路由名称
            item.name = item.navName
        }
        children.push(item)
    })
    return children
}

// 路径规范化处理
function normalizePath(path) {
    // 路径去重。去除路由中的斜杠
    return path.replace(/\/+/g, "")
}

// 动态路由过滤
function filterDynamicRoutes(routes) {
    return routes.filter(route => true)
}
