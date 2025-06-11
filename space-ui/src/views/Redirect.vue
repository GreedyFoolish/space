<template>

</template>

<script setup>
import { onMounted } from "vue"
import { useRoute } from "vue-router"
import router from "@/router/index.js"
import { getValidDecodeUrl } from "@/utils/redirectUtils.js"

const route = useRoute()

onMounted(() => {
    const { redirect } = route.query
    if (redirect) {
        const safeRedirectUrl = getValidDecodeUrl(redirect)
        // 防止无效或外部链接跳转
        if (!safeRedirectUrl) {
            console.warn("非法的 redirect 地址:", redirect)
            return
        }
        // 防止重复跳转到相同路径
        if (router.currentRoute.value.fullPath === safeRedirectUrl) {
            return
        }
        router.replace(safeRedirectUrl)
    }
})
</script>

<style scoped>

</style>
