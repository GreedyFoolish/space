<template>
    <div class="criteria-item" ref="criteriaItemRef">
        <!-- 连接父组件 -->
        <div class="parent-connector" :class="getParentClass()"></div>
        <!-- 连接兄弟组件 -->
        <div class="sibling-connector" :class="getSiblingClass()"></div>
        <!-- 条件项逻辑 -->
        <div
            class="criteria-logic"
            :style="getLogicStyle()"
            @click="toggleLogic(criteriaItem?.idPath, criteriaItem?.logic, true)"
        >
            {{ getLogicText(criteriaItem?.logic) }}
        </div>
        <!-- 条件项内容 -->
        <div class="criteria-content">
            <el-select v-model="criteriaItem.type" placeholder="请选择类型">
                <el-option label="元数据类型" value="metadata"></el-option>
                <el-option label="个人库上传" value="personal"></el-option>
            </el-select>
            <el-select v-model="criteriaItem.operator" placeholder="请选择操作符">
                <el-option label="包含" value="diagnose"></el-option>
                <el-option label="不包含" value="include"></el-option>
            </el-select>
            <el-input v-model="criteriaItem.value" placeholder="请输入值"></el-input>
            <el-button @click="addChildCriteria(criteriaItem?.idPath)">子</el-button>
            <el-button @click="removeCriteria(criteriaItem?.idPath, -1, true)">删除</el-button>
            <el-button @click="addCriteria(criteriaItem?.idPath, true)">加</el-button>
        </div>
        <!-- 子条件项 -->
        <CriteriaItem
            class="nested-criteria"
            v-for="(child, childIndex) in criteriaItem?.children"
            :key="child.id"
            :criteriaItem="child"
            :siblingCount="criteriaItem?.children?.length ?? 0"
            :depth="depth + 1"
            :index="childIndex"
            :prevSiblingCount="getPrevSiblingCount(criteriaItem?.children, childIndex)"
            @toggle-logic="(idPath, logic) => toggleLogic(idPath, logic)"
            @add-criteria="(idPath) => addCriteria(idPath)"
            @remove-criteria="(idPath, deleteCount) => removeCriteria(idPath, deleteCount)"
            @add-child-criteria="(idPath) => addChildCriteria(idPath)"
        >
        </CriteriaItem>
    </div>
</template>

<script setup>
import { defineEmits, ref, onMounted } from "vue"
import { getCssVariableValue } from "@/utils/domUtils.js"
import { validateArray, validateNumber } from "@/utils/validate.js";

const props = defineProps({
    // 当前条件项
    criteriaItem: {
        type: Object,
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
    },
    // 同一父级节点的兄弟项数量
    siblingCount: {
        type: Number,
        default: 0
    },
    // 条件项递归深度, 默认值为 5
    maxDepth: {
        type: Number,
        default: 5
    },
    // 当前条件项递归深度
    depth: {
        type: Number,
        default: 0
    },
    // 当前条件项索引
    index: {
        type: Number,
        default: 0
    },
    prevSiblingCount: {
        type: Number,
        default: 0
    }
})

const emits = defineEmits(["toggle-logic", "add-criteria", "remove-criteria", "add-child-criteria", "update-sibling-count"])

// 默认条件项样式配置对象
const DEFAULT_CRITERIA_STYLE = {
    itemGap: 10,
    connectorWidth: 24,
    connectorHeight: 28,
    logicWidth: 16
}
// 逻辑映射对象
const logicMap = {
    and: {
        text: "且",
        toggleTo: "or"
    },
    or: {
        text: "或",
        toggleTo: "and"
    }
}

// ref 对象，用于响应式地处理 DOM 元素或组件实例
const criteriaItemRef = ref(null)

// 条件项样式值，基于默认配置初始化
const criteriaItemGap = ref(DEFAULT_CRITERIA_STYLE.itemGap)
const criteriaConnectorWidth = ref(DEFAULT_CRITERIA_STYLE.connectorWidth)
const criteriaConnectorHeight = ref(DEFAULT_CRITERIA_STYLE.connectorHeight)
const criteriaLogicWidth = ref(DEFAULT_CRITERIA_STYLE.logicWidth)

const getParentIdPath = (idPath) => {
    return idPath.length > 0 ? idPath.slice(0, -1) : []
}

const getPrevSiblingCount = (list = props.criteriaItem?.children, index = 0) => {
    // 验证参数
    validateArray(list)
    validateNumber(index)

    if (index > 0) {
        return list?.[index - 1]?.childCount ?? 0
    }
    return 0
}

const getItemPosition = (index = props.index) => {
    if (index === 0 && props.siblingCount > 1) {
        return "first"
    }
    if (index > 0 && index < props.siblingCount - 1) {
        return "middle"
    }
    if (index > 0 && index === props.siblingCount - 1) {
        return "last"
    }
    return ""
}

const isFirstItem = (index = props.index) => {
    return getItemPosition(index) === "first"
}

const isMiddleItem = (index = props.index) => {
    return getItemPosition(index) === "middle"
}

const isLastItem = (index = props.index) => {
    return getItemPosition(index) === "last"
}

const getParentClass = (index = props.index) => {
    return {
        firstChild: index === 0 && props.depth > 1
    }
}

const getSiblingClass = (index = props.index) => {
    return {
        firstChild: isFirstItem(index),
        middleChild: isMiddleItem(index),
        lastChild: isLastItem(index)
    }
}

const getLogicStyle = (index = props.index) => {
    // 获取样式配置值（优先响应式数据，否则使用默认值）
    const itemGap = criteriaItemGap.value ?? DEFAULT_CRITERIA_STYLE.itemGap
    const connectorWidth = criteriaConnectorWidth.value ?? DEFAULT_CRITERIA_STYLE.connectorWidth
    const connectorHeight = criteriaConnectorHeight.value ?? DEFAULT_CRITERIA_STYLE.connectorHeight
    const logicWidth = criteriaLogicWidth.value ?? DEFAULT_CRITERIA_STYLE.logicWidth
    // 计算垂直间距
    const verticalSiblingSpacing = (connectorHeight * 2 + itemGap) * props.prevSiblingCount + itemGap
    const halfVerticalSpacing = verticalSiblingSpacing / 2
    const halfLogicWidth = logicWidth / 2
    // 计算逻辑关系符默认样式值
    let display = "block"
    let topValue = (halfVerticalSpacing + halfLogicWidth) * -1
    let leftValue = (connectorWidth + halfLogicWidth) * -1
    // 如果是第一个条件项，则根据条件项递归深度进行判断
    if (index === 0) {
        if (props.depth === 1) {
            // 根节点情况下，隐藏逻辑关系符
            display = "none"
        } else {
            // 计算连接父级节点情况下的 top 和 left
            topValue = (connectorHeight + itemGap - logicWidth) / 2 - itemGap
            leftValue = (connectorWidth * 2 + halfLogicWidth) * -1
        }
    }
    // 返回逻辑关系符样式
    return {
        display: display,
        top: `${topValue}px`,
        left: `${leftValue}px`
    }
}

const getLogicText = (logic = props.criteriaItem?.logic ?? "and") => {
    return logicMap[logic]?.text ?? ""
}

const toggleLogic = (idPath = props.criteriaItem?.idPath ?? [], logic = props.criteriaItem?.logic ?? "and", isTriggerItem = false) => {
    // 验证参数
    validateArray(idPath)
    // 如果由当前条件项触发的切换逻辑操作，则获取切换后的逻辑关系符
    if (isTriggerItem) {
        logic = logicMap[logic]?.toggleTo ?? "and"
    }
    emits("toggle-logic", idPath, logic)
}

const addCriteria = (idPath = props.criteriaItem?.idPath ?? [], isTriggerItem = false) => {
    // 验证参数
    validateArray(idPath)
    // 获取当前条件项路径
    let currentIdPath = idPath
    // 如果是由当前条件项触发的新增操作，则获取其父级路径
    if (isTriggerItem) {
        currentIdPath = getParentIdPath(idPath)
    }
    // 触发添加条件事件
    emits("add-criteria", currentIdPath)
    // 触发更新兄弟节点数量事件
    emits("update-sibling-count", currentIdPath, 1)
}

const removeCriteria = (idPath = props.criteriaItem?.idPath ?? [], deleteCount = -1, isTriggerItem = false) => {
    // 验证参数
    validateArray(idPath)
    // 获取当前条件项父级路径
    const currentIdPath = getParentIdPath(idPath)
    // 如果是由当前条件项触发的删除操作，则计算需要删除的子节点数量
    if (isTriggerItem) {
        deleteCount = (props.criteriaItem?.childCount + 1) * -1
    }
    // 触发移除当前条件的事件
    emits("remove-criteria", idPath, deleteCount)
    // 触发更新兄弟节点数量的事件
    emits("update-sibling-count", currentIdPath, deleteCount)
}

const addChildCriteria = (idPath = props.criteriaItem?.idPath ?? []) => {
    // 验证参数
    validateArray(idPath)
    // 判断是否达到最大深度
    if (props.depth >= props.maxDepth) {
        ElMessage({
            message: `最多支持添加${props.maxDepth}级条件`,
            type: "warning"
        })
        return
    }
    // 触发添加子条件事件
    emits("add-child-criteria", idPath)
    // 触发更新兄弟节点数量事件
    emits("update-sibling-count", idPath, 1)
}

onMounted(() => {
    const element = criteriaItemRef.value
    if (element) {
        const getVarValue = (varName) => {
            const value = getCssVariableValue(varName, element)?.value
            const numValue = parseFloat(value)
            return isNaN(numValue) ? 0 : numValue
        }
        // 获取 CSS 变量值
        criteriaItemGap.value = getVarValue("--criteria-item-gap") ?? DEFAULT_CRITERIA_STYLE.itemGap
        criteriaConnectorWidth.value = getVarValue("--criteria-connector-width") ?? DEFAULT_CRITERIA_STYLE.connectorWidth
        criteriaConnectorHeight.value = getVarValue("--criteria-connector-height") ?? DEFAULT_CRITERIA_STYLE.connectorHeight
        criteriaLogicWidth.value = getVarValue("--criteria-logic-width") ?? DEFAULT_CRITERIA_STYLE.logicWidth
    }
})
</script>

<style scoped>
.criteria-item {
    /* 条件项内容区域高度（用于排版布局，不参与动态计算） */
    --criteria-cell-height: 56px;
    /* 条件项内容区域内边距（用于排版布局） */
    --criteria-cell-padding: 12px;
    /* 条件项内容区域背景色 */
    --criteria-cell-background-color: #F3F4F6;
    /* 条件项之间的垂直间距（用于排版布局） */
    --criteria-item-gap: 10px;
    /* 连接线的标准宽度（父子/兄弟关系共用） */
    --criteria-connector-width: 24px;
    /* 连接线的标准高度（父子/兄弟关系共用） */
    --criteria-connector-height: 28px;
    /* 连接线的边框宽度（父子/兄弟关系共用） */
    --criteria-connector-border-width: 1px;
    /* 连接线的边框样式（父子/兄弟关系共用） */
    --criteria-connector-border-style: solid;
    /* 连接线的边框颜色（父子/兄弟关系共用） */
    --criteria-connector-border-color: #1B4EA1;
    /* 连接线的组合边框样式，包含宽度、样式和颜色（父子/兄弟关系共用） */
    --criteria-connector-border: var(--criteria-connector-border-width) var(--criteria-connector-border-style) var(--criteria-connector-border-color);
    /* 连接线的圆角半径，保持视觉一致性（父子/兄弟关系共用） */
    --criteria-connector-border-radius: 8px;
    /* 逻辑运算符的宽度 */
    --criteria-logic-width: 16px;
    /* 逻辑运算符的字体大小 */
    --criteria-logic-font-size: 12px;
    /* 逻辑运算符的字体颜色 */
    --criteria-logic-color: #3D3D3D;
    /* 逻辑运算符的背景色 */
    --criteria-logic-background-color: #D8D8D8;
    /* 逻辑运算符的圆角半径，保持视觉一致性 */
    --criteria-logic-border-radius: 4px;

    margin-left: calc(var(--criteria-connector-width) * 3);
    position: relative;

    .parent-connector {
        width: calc(var(--criteria-connector-width) * 2);
        height: 0;
        position: absolute;

        &.firstChild {
            height: calc(var(--criteria-connector-height) + var(--criteria-item-gap));
            top: calc(var(--criteria-item-gap) * -1);
            left: calc(var(--criteria-connector-width) * -2);
            border-bottom: var(--criteria-connector-border);
            border-left: var(--criteria-connector-border);
            border-bottom-left-radius: var(--criteria-connector-border-radius);
        }
    }

    .sibling-connector {
        width: var(--criteria-connector-width);
        height: 0;
        position: absolute;
        left: calc(var(--criteria-connector-width) * -1);

        &.firstChild {
            top: var(--criteria-connector-height);
            height: calc(100% - var(--criteria-connector-height) + var(--criteria-item-gap));
            border-left: var(--criteria-connector-border);
            border-top: var(--criteria-connector-border);
            border-top-left-radius: var(--criteria-connector-border-radius);
        }

        &.middleChild {
            height: calc(100% + var(--criteria-item-gap));
            border-left: var(--criteria-connector-border);

            &::after {
                content: "";
                width: var(--criteria-connector-width);
                height: var(--criteria-connector-border-width);
                margin-top: var(--criteria-connector-height);
                display: block;
                background-color: var(--criteria-connector-border-color);
            }
        }

        &.lastChild {
            height: var(--criteria-connector-height);
            top: 0;
            border-bottom: var(--criteria-connector-border);
            border-left: var(--criteria-connector-border);
            border-bottom-left-radius: var(--criteria-connector-border-radius);
        }
    }

    .criteria-logic {
        width: var(--criteria-logic-width);
        height: var(--criteria-logic-width);
        position: absolute;
        top: calc((var(--criteria-item-gap) + var(--criteria-logic-width)) / 2 * -1);
        left: calc((var(--criteria-connector-width) + var(--criteria-logic-width) / 2) * -1);
        font-size: var(--criteria-logic-font-size);
        color: var(--criteria-logic-color);
        line-height: var(--criteria-logic-width);
        text-align: center;
        background-color: var(--criteria-logic-background-color);
        border-radius: var(--criteria-logic-border-radius);
        cursor: pointer;
        user-select: none;
        z-index: 9999;
    }

    .criteria-content {
        height: calc(var(--criteria-connector-height) * 2);
        margin-bottom: var(--criteria-item-gap);
        padding: var(--criteria-cell-padding);
        display: flex;
        align-items: center;
        position: relative;
        background-color: var(--criteria-cell-background-color);
        border-radius: var(--criteria-connector-border-radius);
        box-sizing: border-box;
    }
}
</style>
