<template>
    <el-date-picker
        v-model="modelValue"
        type="date"
        placeholder="选择日期"
    >
    </el-date-picker>
</template>

<script setup>
import { defineProps, defineEmits, computed } from "vue"

const props = defineProps({
    modelValue: {
        type: [String, Date],
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
