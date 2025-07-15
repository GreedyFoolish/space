<template>
    <el-input v-model="modelValue" placeholder="请输入文本值"></el-input>
</template>

<script setup>
import { defineProps, defineEmits, computed } from "vue"

const props = defineProps({
    modelValue: {
        type: String,
        default: ""
    }
})

const emit = defineEmits(["update:modelValue"])

const modelValue = computed({
    get() {
        return props.modelValue
    },
    set(value) {
        emit("update:modelValue", value)
    }
})
</script>
