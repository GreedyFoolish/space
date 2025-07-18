<template>
    <div class="criteria-content-container">
        <el-select
            v-model="criteriaItem.type"
            placeholder="请选择类型"
            @change="(type) => typeChange(criteriaItem?.idPath, type)"
        >
            <el-option
                v-for="item in typeMap"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
        </el-select>
        <el-select
            v-model="criteriaItem.operator"
            placeholder="请选择操作"
            @change="(operator) => operatorChange(criteriaItem?.idPath, operator)"
        >
            <el-option
                v-for="item in getOperators(criteriaItem.type)"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
        </el-select>
        <component
            :key="criteriaItem.id"
            :is="getComponent(criteriaItem.type)"
            v-model="criteriaItem.value"
            v-bind="getComponentProps(criteriaItem.type)"
        >
        </component>
    </div>
</template>

<script setup>
import { defineEmits } from "vue"
import { componentMap, fixedOptions, operatorMap, typeMap } from "@/components/CriteriaQuery/config/criteriaConfig.js"
import { useCachedComponent } from "@/utils/componentUtils.js"

const props = defineProps({
    // 当前条件项
    criteriaItem: {
        type: Object,
        required: true,
        default: () => {
            return {
                id: "",
                type: "",
                operator: "",
                value: "",
                logic: "and",
                childCount: 0,
                children: []
            }
        }
    }
})

const emits = defineEmits(["type-change", "operator-change"])

const getOperators = (type = props.criteriaItem?.type ?? "text") => {
    // 确保 type 是字符串
    const validType = typeof type === "string" ? type : "text"
    // 判断 operatorMap 中是否存在该类型
    const hasType = Object.prototype.hasOwnProperty.call(operatorMap, validType)
    // 返回操作符
    return hasType ? operatorMap[validType] || [] : []
}

const getComponent = (type = props.criteriaItem?.type ?? "text") => {
    return useCachedComponent(componentMap)(type)
}

const getComponentProps = (type = props.criteriaItem?.type ?? "text") => {
    if (type === "fixedOptions") {
        return {
            options: fixedOptions ?? []
        }
    }
    return {}
}

const typeChange = (idPath = props.criteriaItem?.idPath ?? [], type = props.criteriaItem?.type ?? "text") => {
    emits("type-change", idPath, type)
}

const operatorChange = (idPath = props.criteriaItem?.idPath ?? [], operator = props.criteriaItem?.operator) => {
    emits("operator-change", idPath, operator)
}
</script>

<style scoped>
.criteria-content-container {
    display: flex;
    justify-content: start;
    align-items: center;
}
</style>
