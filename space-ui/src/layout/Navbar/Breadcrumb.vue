<template>
    <el-breadcrumb class="breadcrumb-container" separator="/">
        <transition-group name="breadcrumb">
            <el-breadcrumb-item v-for="(item, index) in matched" :key="item.path">
                <a v-if="item.navType === 'menu'" @click.prevent="handleLink(item)">{{ item.name }}</a>
                <span v-else>{{ item.name }}</span>
            </el-breadcrumb-item>
        </transition-group>
    </el-breadcrumb>
</template>

<script setup>
import { ref, onMounted, watch } from "vue"
import { useRoute } from "vue-router"
import router from "@/router/index.js"
import { usePermissionStore } from "@/stores/permissionStore.js"

const permissionStore = usePermissionStore()
const route = useRoute()
let matched = ref([])

const handleLink = (item) => {
    router.push(item.path)
}

// 获取每一层级匹配的路由
const getMatched = (pathList, routeList) => {
    let result = []
    // 获取当前层级的路由集合
    let currentRoutes = JSON.parse(JSON.stringify(routeList))
    for (let path of pathList) {
        // 获取当前层级的目标路由
        const targetPath = path.replace("/", "")
        const matchedRoute = currentRoutes.find(item => {
            const routes = item.path.split("/").filter(item => item)
            if (routes.length === 0) {
                return false
            }
            return routes[routes.length - 1] === targetPath
        })
        if (matchedRoute) {
            result.push(matchedRoute)
            if (matchedRoute.children && Array.isArray(matchedRoute.children)) {
                currentRoutes = matchedRoute.children
            } else {
                currentRoutes = []
            }
        }
    }
    return result
}

const initBreadcrumb = () => {
    const pathList = route.path.split("/").filter(item => item)
    if (pathList.length > 0) {
        matched.value = getMatched(pathList, permissionStore.routes)
    } else {
        matched.value = []
    }
}

onMounted(() => {
    initBreadcrumb()
})

watch(
    () => [route.path, permissionStore.routes],
    ([newPath]) => {
        if (newPath.startsWith("/redirect")) {
            console.log("当前为重定向页面不需要目录导航栏")
        } else {
            initBreadcrumb()
        }
    },
    { immediate: true }
)
</script>

<style scoped>
.breadcrumb-container {
    font-size: 14px;
    line-height: 40px;
}
</style>
