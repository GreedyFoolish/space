<template>
    <component v-for="(node, index) in renderedNodes" :is="node" :key="index"></component>
</template>

<script setup>
import { h, computed } from "vue"
import { useAppConfigStore } from "@/stores/appConfigStore.js"

const props = defineProps({
    icon: {
        type: String,
        default: ""
    },
    title: {
        type: String,
        default: ""
    }
})

const renderedNodes = computed(() => {
    const nodes = []
    // 添加图标
    if (props.icon) {
        // 获取图标
        const icon = useAppConfigStore().global.ElIconsVue[props.icon]
        const iconProps = {
            class: "sidebar-icon",
            key: `icon-${props.title}-${props.icon}`
        }
        if (!icon) {
            console.error(`找不到图标：${props.icon}`)
        } else {
            nodes.push(h(icon, { ...iconProps }))
        }
    }
    // 添加标题
    if (props.title) {
        const title = props.title
        const spanProps = {
            class: "sidebar-title",
            key: `title-${props.title}`
        }
        if (title.length > 5) {
            spanProps.title = title
        }
        nodes.push(h("span", { ...spanProps }, title))
    }
    return nodes
})
</script>

<style scoped>
.sidebar-icon {
    width: 16px;
    height: 16px;
    margin-right: 8px;
}
</style>
