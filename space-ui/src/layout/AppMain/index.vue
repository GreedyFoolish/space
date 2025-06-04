<template>
    <section class="app-main">
        <transition name="fade-transform" mode="out-in">
            <keep-alive>
                <router-view :key="key" />
            </keep-alive>
        </transition>
    </section>
</template>

<script setup>
import { ref, onBeforeMount } from "vue"
import { useRoute } from "vue-router"
import { useAppConfigStore } from "@/stores/appConfigStore.js"

const appConfig = useAppConfigStore()
const key = ref("")

onBeforeMount(() => {
    key.value = useRoute().path
})
</script>

<style scoped>
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
