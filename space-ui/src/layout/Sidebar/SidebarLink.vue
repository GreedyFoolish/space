<template>
    <component :is="linkType(to)" v-bind="linkProps(to)">
        <slot></slot>
    </component>
</template>

<script setup>
import { defineProps } from "vue"
import { isExternal } from "@/utils/validate.js"

const props = defineProps({
    to: {
        type: [String, Object],
        required: true
    }
})

const linkType = (to = props.to) => {
    if (isExternal(to)) {
        return "a"
    }
    return "router-link"
}

const linkProps = (to = props.to) => {
    if (isExternal(to)) {
        // 如果是外部链接，则返回a标签
        return {
            href: to,
            target: "_blank",
            rel: "noopener"
        }
    }
    // 如果是内部链接，则返回router-link标签
    return {
        to: "/" + to
    }
}
</script>

<style scoped>

</style>
