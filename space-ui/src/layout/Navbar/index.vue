<template>
    <div class="navbar-container">
        <component class="sidebar-collapsed" :is="sidebarCollapsed ? Expand : Fold" @click="toggleSidebar"></component>
        <Breadcrumb></Breadcrumb>
    </div>
</template>

<script setup>
import { ref } from "vue"
import Breadcrumb from "@/layout/Navbar/Breadcrumb.vue"
import { useAppConfigStore } from "@/stores/appConfigStore.js"
import { throttle } from "@/utils/throttle.js"

const appConfigStore = useAppConfigStore()
const sidebarCollapsed = ref(false)
const Expand = appConfigStore?.global?.ElIconsVue?.Expand
const Fold = appConfigStore?.global?.ElIconsVue?.Fold

const toggleSidebar = throttle(() => {
    try {
        sidebarCollapsed.value = !sidebarCollapsed.value
        appConfigStore.sideBar.collapse = sidebarCollapsed.value
    } catch (error) {
        console.error("菜单项点击处理出错:", error)
    }
})
</script>

<style scoped>
.navbar-container {
    height: var(--navbar-container-height);
    padding: var(--navbar-container-padding);
    display: flex;
    align-items: center;

    .sidebar-collapsed {
        width: var(--navbar-sidebar-collapsed-width);
        height: var(--navbar-sidebar-collapsed-height);
        margin-right: var(--navbar-sidebar-collapsed-margin-right);
        cursor: pointer;
    }
}
</style>
