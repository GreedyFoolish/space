import cache from "@/utils/cache.js"

/**
 * 防重复提交校验函数
 * @param {Object} config axios 请求配置
 * @param {Object} requestObj 当前请求对象
 * @param REPEAT_SUBMIT_INTERVAL 重复提交间隔时间（默认为 1000 毫秒）
 * @returns {Promise<Object>|Object} 返回配置对象或 Promise 对象
 */
export function checkRepeatSubmit(config, requestObj, REPEAT_SUBMIT_INTERVAL = 1000) {
    // 重复提交间隔时间
    const sessionKey = `sessionObj_${config.url}_${requestObj.data}`
    const sessionObj = cache.session.getJSON(sessionKey)

    if (sessionObj) {
        const { data: s_data, time: s_time, url: s_url } = sessionObj
        if (
            s_data === requestObj.data && s_url === requestObj.url
            && requestObj.time - s_time <= REPEAT_SUBMIT_INTERVAL
        ) {
            const message = "数据正在处理，请勿重复提交"
            console.warn(`[${s_url}]: ` + message)
            return Promise.reject(new Error(message))
        }
    }

    cache.session.setJSON(sessionKey, requestObj)
    return config
}

/**
 * 构建 GET 请求 URL（自动处理 baseURL 和 params）
 * @param {string} url 请求的 URL
 * @param {object} params 请求的参数
 * @returns {string} 构建后的 URL
 */
export function buildGetUrl(url, params) {
    // 参数校验是否有效（排除 undefined、null、NaN、Infinity、-Infinity）
    const isValidValue = (val) => {
        if (typeof val === "number") {
            // 判断是否为有限数字
            return Number.isFinite(val)
        }
        return val !== undefined && val !== null
    }
    // 参数编码
    const encodeParam = (key, value) => {
        const encodedKey = encodeURIComponent(key)
        if (Array.isArray(value)) {
            return `${encodedKey}=${value.map(encodeURIComponent).join(",")}`
        } else if (typeof value === "object") {
            // 对象不做处理，避免出现 [object Object]
            return null
        } else if (typeof value === "boolean") {
            // 显式转为字符串，便于理解
            return `${encodedKey}=${String(value)}`
        } else {
            return `${encodedKey}=${encodeURIComponent(value)}`
        }
    }
    // 处理并编码所有有效参数
    const validParams = Object.entries(params)
        .filter(([_, value]) => isValidValue(value))
        .map(([key, value]) => encodeParam(key, value))
    // 构建查询字符串
    const queryString = validParams.length > 0 ? `?${validParams.join("&")}` : ""
    // 返回构建后的 URL
    return url + queryString
}

/**
 * 计算 UTF-8 字符串的字节长度
 * @param {string} str 需要计算的字符串
 * @returns {number} 字符串的字节长度
 */
export function calculateByteLength(str) {
    let length = 0
    for (let i = 0; i < str.length; i++) {
        const charCode = str.charCodeAt(i)
        if (charCode <= 0x7f) {
            length += 1
        } else if (charCode <= 0x7ff) {
            length += 2
        } else if (charCode <= 0xffff) {
            length += 3
        } else {
            length += 4
        }
    }
    return length
}
