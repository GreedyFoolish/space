import {defineStore} from "pinia"
import {getRouterList} from "@/api/system/router.js"
import InnerLink from "@/layout/InnerLink.vue"
import ParentView from "@/layout/ParentView.vue"
import Layout from "@/layout/index.vue"
import router, {constantRoutes, dynamicRoutes} from "@/router"

const componentMap = {
    Layout,
    ParentView,
    InnerLink
}

// 加载路由组件
const loadView = view => () => import(/* @vite-ignore */ `@/views/${view}`)

export const usePermissionStore = defineStore("permission", {
    state: () => ({
        routes: []
    }),
    actions: {
        generateRouteList() {
            return new Promise(resolve => {
                try {
                    getRouterList().then(res => {
                        // 重写路由
                        const rewriteRoutes = filterAsyncRouter(res.data, false, true)
                        // 过滤动态路由
                        const asyncRoutes = filterDynamicRoutes(dynamicRoutes)
                        // 添加 404 路由
                        rewriteRoutes.push({path: "*", redirect: "/404", hidden: true})
                        // 添加动态路由
                        asyncRoutes.forEach(route => router.addRoute(route))
                        // 设置最终路由
                        this.routes = constantRoutes.concat(rewriteRoutes)
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
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
    return asyncRouterMap.filter(route => {
        if (!checkPermission(route)) {
            return false
        }

        if (type && route.children) {
            route.children = filterChildren(route.children)
        }

        if (route.component) {
            if (componentMap[route.component]) {
                route.component = componentMap[route.component]
            } else {
                route.component = loadView(route.component)
            }
        }

        if (route.children?.length > 0) {
            route.children = filterAsyncRouter(route.children, route, type)
        } else {
            delete route["children"]
            delete route["redirect"]
        }

        return true
    })
}

// 权限校验函数
function checkPermission(route) {
    return true
}

// 子路由路径拼接
function filterChildren(childrenMap) {
    const children = []
    childrenMap.forEach(el => {
        el.path = normalizePath(el.path) // 避免路径重复
        if (el.children && el.children.length && el.component === "ParentView") {
            children.push(...filterChildren(el.children))
        } else {
            children.push(el)
        }
    })
    return children
}

// 路径规范化处理
function normalizePath(path) {
    return path.replace(/\/+/g, "/")
}

// 动态路由过滤
function filterDynamicRoutes(routes) {
    return routes.filter(route => true)
}
