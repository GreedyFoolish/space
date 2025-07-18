import { defineAsyncComponent } from "vue"

// 缓存已创建的组件
const componentCache = {}

// 定义一个统一的加载失败组件生成函数
const createEmptyComponent = () => {
    return defineAsyncComponent(() => Promise.resolve({}))
}

/**
 * 使用缓存的组件或加载新组件
 * @param {Object} componentMap 映射对象，包含组件类型和其对应加载器的映射
 * @returns {Function} 返回指定组件类型的函数
 */
export function useCachedComponent(componentMap) {
    return (type) => {
        // 如果组件已缓存（包括成功或失败），直接返回
        if (Reflect.has(componentCache, type)) {
            return componentCache[type]
        }

        // 获取指定类型的组件加载器
        const loader = componentMap[type]
        // 如果没有 loader，缓存空组件
        if (!loader) {
            componentCache[type] = createEmptyComponent()
            return componentCache[type]
        }

        // 创建异步组件加载器
        componentCache[type] = defineAsyncComponent(async () => {
            try {
                // 尝试加载组件
                return await loader()
            } catch (e) {
                // 加载失败时，记录错误日志
                console.error(`加载组件失败：${type}`, e)
                // 缓存失败状态，防止重复加载
                componentCache[type] = createEmptyComponent()
                return componentCache[type]
            }
        })

        // 返回异步组件
        return componentCache[type]
    }
}
