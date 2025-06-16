<template>
    <div class="app-wrapper">
        <TopNavbar></TopNavbar>
        <div class="container-wrapper">
            <Sidebar :class="sidebarClass" class="sidebar-container"></Sidebar>
            <div class="main-container">
                <div>
                    <Navbar></Navbar>
                    <TagsView></TagsView>
                </div>
                <AppMain></AppMain>
            </div>
        </div>
    </div>
</template>

<script setup>
import { computed } from "vue"
import AppMain from "@/layout/AppMain/index.vue"
import Navbar from "@/layout/Navbar/index.vue"
import Sidebar from "@/layout/Sidebar/index.vue"
import TagsView from "@/layout/TagsView/index.vue"
import TopNavbar from "@/layout/TopNavbar/index.vue"
import { useAppConfigStore } from "@/stores/appConfigStore.js"

const appConfigStore = useAppConfigStore()
const sidebarClass = computed(() => {
    return {
        collapse: appConfigStore.sideBar.collapse
    }
})
</script>

<style scoped>
.app-wrapper {
    height: 100vh;
    display: flex;
    flex-direction: column;

    .container-wrapper {
        width: 100%;
        height: calc(100vh - var(--top-navbar-height));
        display: flex;
        position: relative;

        .sidebar-container {
            width: var(--sidebar-container-width);
            height: 100%;
            flex-shrink: 0;
            background-color: var(--sidebar-container-background-color);
            transition: var(--sidebar-container-transition);

            &.collapse {
                width: var(--sidebar-container-collapse-width);
            }
        }

        .main-container {
            flex: 1;
            transition: var(--sidebar-container-transition);
        }
    }
}

:deep(.el-scrollbar__view) {
    height: 100%;

    .el-menu {
        height: 100%;
    }
}
</style>
