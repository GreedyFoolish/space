/**
 * 生成一个 UUID（通用唯一识别码）
 *
 * UUID的格式为xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx，其中：
 * - x表示一个随机的十六进制数字
 * - y表示一个随机的十六进制数字，但其二进制表示的高两位需为01（确保UUID的版本为4）
 *
 * @returns {string} 生成的 UUID 字符串
 */
export const generateUUID = () => {
    // 定义 UUID 的格式
    const uuidPattern = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx"
    // 获取当前时间的毫秒数，用于生成随机数种子
    let d = new Date().getTime()
    // 使用正则表达式匹配 UUID 模式中的 x 和 y，并用随机数替换它们
    return uuidPattern.replace(/[xy]/g, c => {
        // 生成一个基于当前时间和随机数的十六进制数字
        const r = (d + Math.random() * 16) % 16 | 0
        // 根据 UUID 规范，计算 x 和 y 对应的随机十六进制数字
        const v = c === "x" ? r : (r & 0x3) | 0x8
        // 将计算出的数字转换为十六进制字符串
        return v.toString(16)
    })
}
