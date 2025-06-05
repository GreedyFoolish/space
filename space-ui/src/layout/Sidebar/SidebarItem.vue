<template>
    <template v-if="showChild(item)">
        <SidebarLink :to="resolvePath(onlyOneChildren.navUrl, onlyOneChildren.query)">
            <el-menu-item :index="resolvePath(onlyOneChildren.navUrl)">
                <SidebarText :icon="onlyOneChildren.navIcon" :title="onlyOneChildren.navName"></SidebarText>
            </el-menu-item>
        </SidebarLink>
    </template>
    <template v-else>
        <el-sub-menu :index="resolvePath(item.navUrl)" teleported>
            <template #title>
                <SidebarText :icon="item.navIcon" :title="item.navName"></SidebarText>
            </template>
            <SidebarItem v-for="child in item.children"
                         :key="child.navUrl"
                         :item="child"
                         :basePath="resolvePath(item.navUrl)"
            >
            </SidebarItem>
        </el-sub-menu>
    </template>
</template>

<script setup>
import { ref } from "vue"
import SidebarLink from "@/layout/Sidebar/SidebarLink.vue"
import SidebarText from "@/layout/Sidebar/SidebarText.vue"
import { isExternal } from "@/utils/validate.js"

const props = defineProps({
    item: {
        type: Object,
        required: true
    },
    basePath: {
        type: String,
        default: ""
    }
})
const onlyOneChildren = ref(null)

const showChild = (item = props.item) => {
    return hasOneShowChildren(item.children, item)
        && (!onlyOneChildren.value.children || onlyOneChildren.value.noShowChildren)
}

const hasOneShowChildren = (children = [], parent) => {
    if (!children) {
        children = []
    }
    const showingChildren = children.filter(item => {
        if (item.hidden) {
            return false
        }
        // 如果只有一个需要展示的子菜单，则保存这个子路由，用于默认打开
        onlyOneChildren.value = item
        return true
    })
    // 当只有一个子路由时，默认显示子路由
    if (showingChildren.length === 1) {
        // return true
    }
    // 如果没有要显示的子路由，则显示父路由
    if (showingChildren.length === 0) {
        onlyOneChildren.value = { ...parent, noShowChildren: true }
        return true
    }
    // 如果有多个子路由，则不显示
    return false
}

const customResolvePath = (basePath, routePath) => {
    // 去掉父路由开头和末尾的所有斜杠 /
    const safeBase = basePath ? basePath.replace(/^\/+|\/+$/g, "") : ""
    // 去掉子路由开头的所有斜杠 /
    const safeRoute = routePath ? routePath.replace(/^\/+/, "") : ""
    // 如果任意一个为空，则直接返回另一个
    if (!safeBase || !safeRoute) {
        return safeBase || safeRoute
    }
    // 返回拼接后的路径
    return `/${safeBase}/${safeRoute}`
}

const resolvePath = (routePath, routeQuery = null) => {
    if (!routePath) {
        console.error("无效的路由路径：", routePath)
        return ""
    }
    // 如果路由路径是一个外部链接，则直接返回路由路径
    if (isExternal(routePath)) {
        return routePath
    }
    // 如果父路由路径是一个外部链接，则直接返回父路由路径
    if (props.basePath && isExternal(props.basePath)) {
        return props.basePath
    }
    // 解析路由查询参数
    let query = {}
    // 如果路由查询参数是一个字符串，则尝试解析为对象
    if (typeof routeQuery === "string") {
        try {
            query = JSON.parse(routeQuery)
        } catch (e) {
            console.warn("无效的路由查询参数：", routeQuery, e)
            query = {}
        }
    } else if (routeQuery !== null && typeof routeQuery !== "object") {
        console.warn("routeQuery 类型应为字符串或 null：", routeQuery)
        query = {}
    }
    // 返回拼接后的路径
    const resolvedPath = customResolvePath(props.basePath, routePath)
    // 返回拼接后的路径和查询参数
    return routeQuery != null ? { path: resolvedPath, query } : resolvedPath
}
</script>

<style scoped>

</style>
