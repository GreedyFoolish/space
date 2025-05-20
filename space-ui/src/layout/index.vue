<template>
    <div :class="classObj" class="app-wrapper">
        <TopNavbar></TopNavbar>
        <div class="drawer-bg" @click="handleClickOutSide"></div>
        <Sidebar class="sidebar-container"></Sidebar>
        <div class="main-container">
            <div>
                <!--                <Navbar @setLayout="setLayout"/>-->
                <!--                <TagsView v-if="needTagsView"/>-->
            </div>
            <AppMain></AppMain>
        </div>
    </div>
</template>

<script setup>
import AppMain from '@/layout/AppMain/index.vue'
import Navbar from '@/layout/Navbar/index.vue'
import Sidebar from '@/layout/Sidebar/index.vue'
import TagsView from '@/layout/TagsView/index.vue'
import TopNavbar from '@/layout/TopNavbar/index.vue'

const classObj = () => {
    return {
        hideSidebar: true,
        openSidebar: false,
        withoutAnimation: false
    }
}

const handleClickOutSide = () => {

}
</script>

<style scoped>
.app-wrapper {
    height: 100px;
    display: flex;
}
</style>
