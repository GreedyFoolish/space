// 默认条件项样式配置对象
export const DEFAULT_CRITERIA_STYLE = {
    itemGap: 10,
    connectorWidth: 24,
    connectorHeight: 28,
    logicWidth: 16
}

// 逻辑映射对象
export const logicMap = {
    and: {
        text: "且",
        toggleTo: "or"
    },
    or: {
        text: "或",
        toggleTo: "and"
    }
}

// 类型映射对象
export const typeMap = [
    {
        label: "文本",
        value: "text"
    },
    {
        label: "日期",
        value: "date"
    },
    {
        label: "日期时间",
        value: "datetime"
    },
    {
        label: "数值",
        value: "number"
    },
    {
        label: "固定选项",
        value: "fixedOptions"
    },
    {
        label: "是非判断",
        value: "boolean"
    }
]

// 操作符映射对象
export const operatorMap = {
    text: [
        { label: "包含", value: "contains" },
        { label: "不包含", value: "notContains" },
        { label: "等于", value: "equals" },
        { label: "不等于", value: "notEquals" }
    ],
    date: [
        { label: "等于", value: "equals" },
        { label: "不等于", value: "notEquals" },
        { label: "大于", value: "greaterThan" },
        { label: "小于", value: "lessThan" },
        { label: "大于等于", value: "greaterThanOrEquals" },
        { label: "小于等于", value: "lessThanOrEquals" }
    ],
    datetime: [
        { label: "等于", value: "equals" },
        { label: "不等于", value: "notEquals" },
        { label: "大于", value: "greaterThan" },
        { label: "小于", value: "lessThan" },
        { label: "大于等于", value: "greaterThanOrEquals" },
        { label: "小于等于", value: "lessThanOrEquals" }
    ],
    number: [
        { label: "等于", value: "equals" },
        { label: "不等于", value: "notEquals" },
        { label: "大于", value: "greaterThan" },
        { label: "小于", value: "lessThan" },
        { label: "大于等于", value: "greaterThanOrEquals" },
        { label: "小于等于", value: "lessThanOrEquals" }
    ],
    fixedOptions: [
        { label: "等于", value: "equals" },
        { label: "不等于", value: "notEquals" }
    ],
    boolean: [
        { label: "等于", value: "equals" },
        { label: "不等于", value: "notEquals" }
    ]
}

// 组件映射对象
export const componentMap = {
    text: () => import("@/components/CriteriaQuery/components/TextInput.vue"),
    date: () => import("@/components/CriteriaQuery/components/DateInput.vue"),
    datetime: () => import("@/components/CriteriaQuery/components/DateTimeInput.vue"),
    number: () => import("@/components/CriteriaQuery/components/NumberInput.vue"),
    fixedOptions: () => import("@/components/CriteriaQuery/components/FixedOptionsInput.vue"),
    boolean: () => import("@/components/CriteriaQuery/components/BooleanInput.vue")
}

// 固定选项数据
export const fixedOptions = [
    { label: "启用", value: "enabled" },
    { label: "停用", value: "disabled" }
]
