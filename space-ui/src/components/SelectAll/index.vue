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
// 用于记录是否全选状态
const allSelected = ref(false)

const allOptions = computed(() => {
    return props.options.filter(item => item.value !== props.selectAllValue)
})

const allOptionsValue = computed(() => {
    return allOptions.value.map(item => item.value)
})

// 构建全部选中选项
const buildFullSelection = () => {
    return [props.selectAllValue, ...allOptionsValue.value]
}

// 构建非全选项的选中选项
const buildNormalSelection = (values) => {
    if (!Array.isArray(values)) {
        return []
    }
    return values.filter(v => v !== props.selectAllValue)
}

const handleChange = (value) => {
    // 初始化选中值
    let newSelected = []
    // 获取非全选项的选中值
    const allNormalValue = buildNormalSelection(value)
    // 更新选中选项及全选状态
    if (value.includes(props.selectAllValue)) {
        // 选中选项中包含全选项
        if (allSelected.value) {
            // 全选状态下，则切换到非全选状态并更新选中选项
            newSelected = allNormalValue
            allSelected.value = false
        } else {
            // 非全选状态下，则切换到全选状态并选中全部选项
            newSelected = buildFullSelection()
            allSelected.value = true
        }
    } else {
        // 选中选项中不包含全选项
        if (props.invert && allSelected.value) {
            // 设置反选选项且全选状态下，则切换到非全选状态并清空选中选项
            newSelected = []
            allSelected.value = false
        } else if (allNormalValue.length === allOptions.value.length) {
            // 非全选状态下且选中的值与全选项的值一致，则切换到全选状态并选中全部选项
            newSelected = buildFullSelection()
            allSelected.value = true
        } else {
            // 非全选状态下且选中的值与全选项的值不一致，则切换到非全选状态并更新选中选项
            newSelected = allNormalValue
            allSelected.value = false
        }
    }
    // 更新选中选项
    selectedValues.value = newSelected
    // 更新绑定值
    emit("update:modelValue", newSelected.filter(v => v !== props.selectAllValue))
}

watch(() => props.modelValue, (newValue) => {
    // 初始化值
    let initValues = []
    // 处理初始化值
    if (Array.isArray(newValue)) {
        initValues = [...newValue]
    } else if (typeof newValue === "string") {
        initValues = [newValue]
    } else {
        throw new Error("modelValue 必须是数组或字符串")
    }
    // 获取非全选项的初始化值
    const filteredInit = buildNormalSelection(initValues)
    // 初始化选项及全选状态
    if (initValues.includes(props.selectAllValue) || (filteredInit.length === allOptions.value.length)) {
        // 始化值存在全选项或初始化值的长度与全选项的值一致，则初始化为全选状态
        selectedValues.value = buildFullSelection()
        allSelected.value = true
    } else {
        // 初始化为非全选状态
        selectedValues.value = filteredInit
        allSelected.value = false
    }
}, { immediate: true })
</script>

<style scoped>

</style>
