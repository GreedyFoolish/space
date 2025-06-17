<template>
    <el-select
        v-model="selectedValues"
        :placeholder="placeholder"
        multiple
        :filterable="filterable"
        :clearable="clearable"
        :collapse-tags="collapseTags"
        :max-collapse-tags="maxCollapseTags"
        :popper-class="popperClass"
        :style="customStyle"
        @change="handleChange"
    >
        <!-- 全选选项 -->
        <el-option
            v-if="showSelectAll"
            key="select-all"
            :label="selectAllLabel"
            :value="selectAllValue"
        ></el-option>
        <!-- 数据项 -->
        <el-option
            v-for="item in allOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        ></el-option>
    </el-select>
</template>

<script setup>
import { ref, computed, watch } from "vue"

const props = defineProps({
    // 绑定值
    modelValue: {
        type: [Array, String],
        default: () => []
    },
    // 占位符
    placeholder: {
        type: String,
        default: "请选择"
    },
    // 是否反选
    invert: {
        type: Boolean,
        default: true
    },
    // 是否可搜索
    filterable: {
        type: Boolean,
        default: false
    },
    // 是否可清空
    clearable: {
        type: Boolean,
        default: false
    },
    // 是否折叠
    collapseTags: {
        type: Boolean,
        default: false
    },
    // 最大折叠数
    maxCollapseTags: {
        type: Number,
        default: 1
    },
    // 自定义类名
    popperClass: {
        type: String,
        default: ""
    },
    // 样式
    customStyle: {
        type: String,
        default: ""
    },
    // 是否显示全选
    showSelectAll: {
        type: Boolean,
        default: true
    },
    // 全选标签
    selectAllLabel: {
        type: String,
        default: "全选"
    },
    // 全选值
    selectAllValue: {
        type: String,
        default: "select-all"
    },
    // 数据项
    options: {
        type: Array,
        default: () => []
    }
})
const emit = defineEmits(["update:modelValue", "change"])
const selectedValues = ref([])
const allSelected = ref(false)
const allOptions = computed(() => {
    return props.options.filter(item => item.value !== props.selectAllValue)
})
const allOptionsValue = computed(() => {
    return allOptions.value.map(item => item.value)
})
const handleChange = (value) => {
    // 获取非全选项的选中值
    const allValue = value.filter(item => item !== props.selectAllValue)
    if (value.includes(props.selectAllValue)) {
        // 选中值中包含全选项
        if (allSelected.value) {
            // 全选状态下，则切换到非全选状态并更新选中选项
            selectedValues.value = allValue
            allSelected.value = false
        } else {
            // 非全选状态下，则切换到全选状态并选中全部选项
            selectedValues.value = [props.selectAllValue, ...allOptionsValue.value]
            allSelected.value = true
        }
    } else {
        // 选中的值中不包含全选项
        if (props.invert && allSelected.value) {
            // 设置反选选项且全选状态下，则切换到非全选状态并清空选中选项
            selectedValues.value = []
            allSelected.value = false
        } else if (allValue.length === allOptions.value.length) {
            // 非全选状态下且选中的值与全选项的值一致，则切换到全选状态并选中全部选项
            selectedValues.value = [props.selectAllValue, ...allOptionsValue.value]
            allSelected.value = true
        } else {
            // 非全选状态下且选中的值与全选项的值不一致，则切换到非全选状态并更新选中选项
            selectedValues.value = allValue
            allSelected.value = false
        }
    }
    emit("update:modelValue", selectedValues.value.filter(item => item !== props.selectAllValue))
}

watch(() => props.modelValue, (newValue) => {
    if (newValue instanceof Array) {
        // 获取非全选项的初始化值
        const allInit = newValue.filter(item => item !== props.selectAllValue)
        if (allInit.length === allOptions.value.length || newValue.includes(props.selectAllValue)) {
            // 初始化值的长度与全选项的值一致或存在全选项，则初始化为全选状态
            selectedValues.value = [props.selectAllValue, ...allOptionsValue.value]
            allSelected.value = true
        } else {
            // 初始化为非全选状态
            selectedValues.value = newValue
            allSelected.value = false
        }
    } else if (typeof newValue === "string") {
        if ((allOptions.value.length === 1 && allOptionsValue.value.includes(newValue))
            || newValue === props.selectAllValue
        ) {
            // 选项的长度为1且值为初始化值或初始化值为全选项，则初始化为全选状态
            selectedValues.value = [props.selectAllValue, newValue]
            allSelected.value = true
        } else {
            // 初始化为非全选状态
            selectedValues.value = [newValue]
            allSelected.value = false
        }
    } else {
        throw new Error("modelValue必须是数组或字符串")
    }
}, { immediate: true })
</script>

<style scoped>

</style>
