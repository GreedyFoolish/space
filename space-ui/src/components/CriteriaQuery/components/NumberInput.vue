<template>
    <el-input-number v-model="modelValue" placeholder="请输入数值"></el-input-number>
</template>

<script setup>
import { defineProps, defineEmits, computed } from "vue"

const props = defineProps({
    modelValue: {
        type: Number,
        default: null
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
