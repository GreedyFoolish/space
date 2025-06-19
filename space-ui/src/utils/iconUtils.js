import { ref, shallowRef, watchEffect } from "vue"

const appConfigStore = ref(null)

export const setAppConfigStore = (store) => {
    appConfigStore.value = store
}

// 使用 shallowRef 避免每次 computed 都创建新对象
const iconMapRef = shallowRef({})

// 提取图标映射构建逻辑，提高可维护性和可测试性
const buildIconMap = () => {
    // 检查 appConfigStore.value 是否为 null 或 undefined
    if (!appConfigStore.value || !appConfigStore.value.global?.ElIconsVue) {
        return {}
    }

    const icons = appConfigStore.value.global.ElIconsVue
    // 检查 icons 是否是一个对象，并且不是数组或 null
    if (typeof icons !== "object" || Array.isArray(icons) || icons === null) {
        return {}
    }
    // 创建一个浅引用的映射对象
    return Object.entries(icons).reduce((acc, [key, value]) => {
        acc[key] = shallowRef(value)
        return acc
    }, {})
}

// 单独监听 appConfigStore 变化来更新 iconMapRef
watchEffect(() => {
    iconMapRef.value = buildIconMap()
})

// 获取图标安全方法
export const getIcon = (iconName) => {
    if (!iconName || !iconMapRef.value[iconName]) {
        // 返回一个默认图标或返回null让组件处理
        return null
    }
    return iconMapRef.value[iconName].value
}
