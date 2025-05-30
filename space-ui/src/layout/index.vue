<template>
    <div :class="classObj" class="app-wrapper">
        <TopNavbar></TopNavbar>
        <div class="container-wrapper">
            <div class="drawer-wrapper" @click="handleClickOutSide"></div>
            <Sidebar class="sidebar-container"></Sidebar>
            <div class="main-container">
                <div>
                    <!--                <Navbar @setLayout="setLayout"/>-->
                    <!--                <TagsView v-if="needTagsView"/>-->
                </div>
                <AppMain></AppMain>
            </div>
        </div>
    </div>
</template>

<script setup>
import AppMain from "@/layout/AppMain/index.vue"
import Navbar from "@/layout/Navbar/index.vue"
import Sidebar from "@/layout/Sidebar/index.vue"
import TagsView from "@/layout/TagsView/index.vue"
import TopNavbar from "@/layout/TopNavbar/index.vue"

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
    height: 100vh;
    display: flex;
    flex-direction: column;

    .container-wrapper {
        width: 100%;
        height: calc(100vh - var(--top-navbar-height));
        display: flex;
        position: relative;

        .drawer-wrapper {
            width: 100%;
            height: 100%;
            position: absolute;
            background-color: #000000;
            opacity: 0.3;
        }

        .sidebar-container {
            width: 200px;
            height: 100%;
            background-color: var(--sidebar-container-background-color);
            transition: var(--sidebar-container-transition);
        }

        .main-container {
            flex: 1;
            transition: var(--sidebar-container-transition);
        }
    }
}
</style>
