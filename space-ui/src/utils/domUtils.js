// 支持的单位集合（提取为常量）
const supportedUnits = new Set([
    "px", "em", "rem", "vw", "vh", "%", "deg", "rad", "grad", "turn",
    "s", "ms", "Hz", "kHz", "dpi", "dpcm", "dppx", "x",
    "cm", "mm", "in", "pc", "pt", "Q"
])

// 构建更精确的正则表达式（缓存）
const unitPattern = Array.from(supportedUnits).join("|")
// 更严格的正则表达式，支持科学计数法并确保单位紧随其后
const unitRegex = new RegExp(`^([-+]?\\d*\\.?\\d+(?:[eE][-+]?\\d+)?)(${unitPattern})?$`, "i")

/**
 * 获取指定 CSS 变量的值，并尝试解析出数值和单位
 * 支持常见单位如 px, rem, em, %, vh, vw 等
 * @param {string} variableName CSS变量名（如 "--top-navbar-menu-item-width"）
 * @returns {{unit: (string), value: (null | number | string | boolean)}} 包含数值和单位的对象
 */
export const getCssVariableValue = (variableName) => {
    if (typeof variableName !== "string" || !variableName.startsWith("--")) {
        console.warn(`CSS变量名无效：${variableName}`)
        return { value: null, unit: "" }
    }

    // 获取CSS变量的值
    let rawValue
    try {
        rawValue = getComputedStyle(document.documentElement).getPropertyValue(variableName).trim()
    } catch (e) {
        console.error("获取CSS变量失败", e)
        return { value: null, unit: "" }
    }

    // 处理空字符串
    if (!rawValue) {
        return { value: null, unit: "" }
    }

    // 尝试匹配数值+单位
    const match = rawValue.match(unitRegex)
    if (match) {
        const numericValue = parseFloat(match[1])
        const unit = match[2] ? match[2].toLowerCase() : ""
        return { value: isNaN(numericValue) ? null : numericValue, unit }
    }

    // 处理布尔值
    const lowerValue = rawValue.toLowerCase()
    if (lowerValue === "true") {
        return { value: true, unit: "boolean" }
    }
    if (lowerValue === "false") {
        return { value: false, unit: "boolean" }
    }

    // 默认返回字符串原始值
    return { value: rawValue, unit: "string" }
}
