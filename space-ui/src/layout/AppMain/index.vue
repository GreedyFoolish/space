<template>
    <section class="app-main">
        <router-view :key="routeKey" v-slot="{ Component }">
            <transition name="fade-transform" mode="out-in">
                <keep-alive>
                    <component :is="Component" v-if="route.meta?.keepAlive" />
                    <component :is="Component" v-else />
                </keep-alive>
            </transition>
        </router-view>
    </section>
</template>

<script setup>
import { computed } from "vue"
import { useRoute } from "vue-router"
import { useAppConfigStore } from "@/stores/appConfigStore.js"

const appConfig = useAppConfigStore()
const route = useRoute()
const routeKey = computed(() => route.path)
</script>

<style scoped>
.app-main {
    width: var(--app-main-width);
    height: calc(100vh - var(--top-navbar-height) - var(--navbar-container-height) - var(--tags-view-container-height));
    padding: var(--app-main-padding);
    overflow: auto;
    box-sizing: border-box;
}

.fade-transform--move,
.fade-transform-leave-active,
.fade-transform-enter-active {
    transition: all 0.5s;
}

.fade-transform-enter {
    opacity: 0;
    transform: translateX(-30px);
}

.fade-transform-leave-to {
    opacity: 0;
    transform: translateX(30px);
}
</style>
