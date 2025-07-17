<template>
    <div class="criteria-container">
        <CriteriaItem
            v-for="(item, index) in criteriaListWithId"
            :key="item.id"
            :criteriaItem="item"
            :siblingCount="criteriaListWithId?.length ?? 0"
            :depth="1"
            :index="index"
            :prevSiblingCount="getPrevSiblingCount(criteriaListWithId, index)"
            @toggle-logic="handleToggleLogic"
            @type-change="(idPath, type) => handleTypeChange(idPath, type)"
            @operator-change="(idPath, operator) => handleOperatorChange(idPath, operator)"
            @add-criteria="handleAddChildCriteria"
            @remove-criteria="handleRemoveCriteria"
            @add-child-criteria="handleAddChildCriteria"
            @update-sibling-count="(idPath, change) => handleUpdateSiblingCount(idPath, change)"
        >
            <template #criteria-content="{ criteriaItem }">
                <slot name="criteria-content" :criteriaItem="criteriaItem">
                </slot>
            </template>
        </CriteriaItem>
    </div>
</template>

<script setup>
import { defineEmits, ref, onBeforeMount } from "vue"
import CriteriaItem from "@/components/CriteriaQuery/CriteriaItem.vue"
import { generateUUID } from "@/utils/uuidUtils.js"
import { validateArray, validateNumber } from "@/utils/validate.js"

const props = defineProps({
    // 每页显示个数选择器的选项设置
    criteriaList: {
        type: Array,
        default: () => [
            {
                id: "",
                idPath: [],
                type: "text",
                operator: "",
                value: "",
                logic: "and",
                childCount: 0,
                children: []
            }
        ]
    },
    // 是否自动滚动到顶部
    autoScroll: {
        type: Boolean,
        default: true
    }
})

const emits = defineEmits(["listDataChange", "update:criteriaList"])

const criteriaListWithId = ref([])

const getPrevSiblingCount = (list = criteriaListWithId.value, index = 0) => {
    // 验证参数
    validateArray(list)
    validateNumber(index)

    if (index > 0) {
        return list?.[index - 1]?.childCount ?? 0
    }
    return 0
}

const findNodeByPath = (path, root = criteriaListWithId.value) => {
    let currentLevel = root
    // 遍历路径，找到对应id的节点
    for (let i = 0; i < path.length; i++) {
        const id = path[i]
        // 在当前层级中寻找对应id的节点
        const index = currentLevel.findIndex(item => item.id === id)
        // 没有找到对应id的节点
        if (index === -1) {
            return null
        }
        // 如果找到对应id的节点，则返回该节点
        if (i === path.length - 1) {
            return { node: currentLevel[index], parent: currentLevel, index }
        }
        // 如果当前层级没有子节点，则返回null
        if (!currentLevel[index].children) {
            return null
        }
        // 如果当前层级有子节点，则进入下一层
        currentLevel = currentLevel[index].children
    }
    // 如果没有找到对应的路径，则返回null
    return null
}

const handleToggleLogic = (idPath, logic) => {
    // 使用 findNodeByPath 找到目标节点
    const { node } = findNodeByPath(idPath)
    // 如果找不到目标节点，则进行警告
    if (!node) {
        console.warn("未找到目标节点", idPath)
        return
    }
    // 更新逻辑操作符
    node.logic = logic
    // 触发 Vue 响应式更新
    criteriaListWithId.value = [...criteriaListWithId.value]

    emits("update:criteriaList", criteriaListWithId.value)
    emits("listDataChange", criteriaListWithId.value)
}

const handleTypeChange = (idPath = [], type) => {
    // 使用 findNodeByPath 找到目标节点
    const { node } = findNodeByPath(idPath)
    // 如果找不到目标节点，则进行警告
    if (!node) {
        console.warn("未找到目标节点", idPath)
        return
    }
    node.type = type
    node.operator = ""
    node.value = ""
    // 触发 Vue 响应式更新
    criteriaListWithId.value = [...criteriaListWithId.value]

    emits("update:criteriaList", criteriaListWithId.value)
    emits("listDataChange", criteriaListWithId.value)
}

const handleOperatorChange = (idPath = [], operator) => {
    // 使用 findNodeByPath 找到目标节点
    const { node } = findNodeByPath(idPath)
    // 如果找不到目标节点，则进行警告
    if (!node) {
        console.warn("未找到目标节点", idPath)
        return
    }
    node.operator = operator
    node.value = ""
    // 触发 Vue 响应式更新
    criteriaListWithId.value = [...criteriaListWithId.value]

    emits("update:criteriaList", criteriaListWithId.value)
    emits("listDataChange", criteriaListWithId.value)
}

const handleAddChildCriteria = (idPath = []) => {
    const itemId = generateUUID()
    const newChild = {
        id: itemId,
        idPath: [...idPath, itemId],
        type: "text",
        operator: "",
        value: "",
        logic: "and",
        childCount: 0,
        children: []
    }

    if (idPath.length) {
        // 使用 findNodeByPath 找到目标节点
        const { node } = findNodeByPath(idPath)
        // 如果找不到目标节点，则进行警告
        if (!node) {
            console.warn("未找到目标节点", idPath)
            return
        }
        // 添加子节点
        node.children.push(newChild)
    } else {
        // 如果路径不存在，则直接插入新项
        criteriaListWithId.value.push(newChild)
    }
    // 触发 Vue 响应式更新
    criteriaListWithId.value = [...criteriaListWithId.value]

    emits("update:criteriaList", criteriaListWithId.value)
    emits("listDataChange", criteriaListWithId.value)
}

const handleRemoveCriteria = (idPath = []) => {
    // 如果路径存在，则使用 findNodeByPath 定位目标父级节点并删除
    const { parent, index } = findNodeByPath(idPath)

    if (!parent || index === -1) {
        console.warn("未找到要删除节点的目标父级节点", idPath)
        return
    }

    // 删除目标节点
    parent.splice(index, 1)
    // 触发 Vue 响应式更新
    criteriaListWithId.value = [...criteriaListWithId.value]

    emits("update:criteriaList", criteriaListWithId.value)
    emits("listDataChange", criteriaListWithId.value)
}

const handleUpdateSiblingCount = (idPath = [], change) => {
    // 遍历 idPath 的每个节点并更新 childCount
    for (let i = 0; i < idPath.length; i++) {
        // 构造当前层级路径
        const currentPath = idPath.slice(0, i + 1)
        // 找到对应节点
        const { node } = findNodeByPath(currentPath)
        if (!node) {
            console.warn("未找到目标节点：", currentPath)
            continue
        }
        // 更新 childCount
        node.childCount = node.childCount + change
    }
    // 触发 Vue 响应式更新
    criteriaListWithId.value = [...criteriaListWithId.value]

    emits("update:criteriaList", criteriaListWithId.value)
    emits("listDataChange", criteriaListWithId.value)
}

const initCriteriaWithId = (items, idPath = []) => {
    return items.map(item => {
        const itemId = item.id || generateUUID()
        const itemPath = [...idPath, itemId]
        return {
            ...item,
            id: itemId,
            idPath: itemPath,
            children: item?.children?.length ? initCriteriaWithId(item.children, itemPath) : []
        }
    })
}

onBeforeMount(() => {
    criteriaListWithId.value = initCriteriaWithId(props.criteriaList)
})
</script>

<style scoped>
.criteria-container {
    display: flex;
    flex-wrap: wrap;

    .criteria-item {
        width: 100%;
        position: relative;
    }
}
</style>
