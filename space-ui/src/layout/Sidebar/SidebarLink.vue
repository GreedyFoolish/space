<template>
    <component :is="linkType(to)" :to="linkProps(to)">
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
        return {
            href: to,
            target: "_blank",
            rel: "noopener"
        }
    }
    return {
        to: to
    }
}
</script>

<style scoped>

</style>
