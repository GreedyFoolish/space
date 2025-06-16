<template>
    <el-scrollbar>
        <el-menu :collapse="appConfig.sideBar.collapse" mode="vertical">
            <SidebarItem
                v-for="(item, index) in topMenu"
                :key="`menu-item-${item.id || index}`"
                :item="item"
                :basePath="basePath"
            >
            </SidebarItem>
        </el-menu>
    </el-scrollbar>
</template>

<script setup>
import { ref, computed } from "vue"
import SidebarItem from "@/layout/Sidebar/SidebarItem.vue"
import { useAppConfigStore } from "@/stores/appConfigStore.js"
import { usePermissionStore } from "@/stores/permissionStore.js"

const appConfig = useAppConfigStore()
const permissionStore = usePermissionStore()
const topMenu = computed(() => {
    return permissionStore.topNavbarRoutes
})
const basePath = ref("/")
</script>

<style scoped>

</style>
