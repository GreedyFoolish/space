<template>
    <el-select v-model="modelValue" placeholder="请选择">
        <el-option
            v-for="option in options"
            :key="option.value"
            :label="option.label"
            :value="option.value"
        >
        </el-option>
    </el-select>
</template>

<script setup>
import { defineProps, defineEmits, computed } from "vue"

const props = defineProps({
    modelValue: {
        type: String,
        default: ""
    },
    options: {
        type: Array,
        default: () => []
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
