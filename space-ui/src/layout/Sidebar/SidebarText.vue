<template>
    <component v-for="(node, index) in renderedNodes" :is="node" :key="index"></component>
</template>

<script setup>
import { h, computed } from "vue"

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
    // if (props.icon) {
    //     nodes.push(h(SvgIcon, { iconClass: props.icon }))
    // }
    // 添加标题
    if (props.title) {
        const title = props.title
        const spanProps = {
            class: "sidebar-title"
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

</style>
