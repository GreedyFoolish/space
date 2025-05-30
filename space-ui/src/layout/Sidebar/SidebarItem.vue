<template>
    <template v-if="showChild(item)">
        <SidebarLink :to="resolvePath(onlyOneChildren.navUrl, onlyOneChildren.query)">
            <el-menu-item :index="resolvePath(onlyOneChildren.navUrl)">
                <SidebarText :title="item.navName">{{ item.navName }}</SidebarText>
            </el-menu-item>
        </SidebarLink>
    </template>
    <template v-else>
        <el-sub-menu :index="resolvePath(item.navUrl)" teleported>
            <template #title>
                <SidebarText :title="item.navName"></SidebarText>
            </template>
            <SidebarItem v-for="child in item.children"
                         :key="child.navUrl"
                         :item="child"
                         :basePath="resolvePath(child.navUrl)"
            >
            </SidebarItem>
        </el-sub-menu>
    </template>
</template>

<script setup>
import path from "path"
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
        onlyOneChildren.value = { ...parent, navUrl: "", noShowChildren: true }
        return true
    }
    // 如果有多个子路由，则不显示
    return false
}

const resolvePath = (routePath, routeQuery = null) => {
    if (routePath) {
        if (isExternal(routePath)) {
            return routePath
        }
        if (props.basePath && isExternal(props.basePath)) {
            return props.basePath
        }
        if (routeQuery != null) {
            let query = JSON.parse(routeQuery)
            return { path: path.resolve(props.basePath, routePath), query: query }
        }
        return path.resolve(routePath)
    } else {
        return routePath
    }
}
</script>

<style scoped>

</style>
