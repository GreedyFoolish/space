import cache from "@/utils/cache.js";

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
        const {data: s_data, time: s_time, url: s_url} = sessionObj
        if (
            s_data === requestObj.data &&
            requestObj.time - s_time <= REPEAT_SUBMIT_INTERVAL &&
            s_url === requestObj.url
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
 * @param {string} baseURL 请求的 baseURL
 * @param {string} url 请求的 URL
 * @param {object} params 请求的参数
 * @returns {string} 构建后的 URL
 */
export function buildGetUrl(baseURL, url, params) {
    const fullUrl = new URL(url, baseURL)
    Object.entries(params).forEach(([key, value]) => {
        if (value !== undefined) {
            fullUrl.searchParams.append(key, value)
        }
    })
    return fullUrl.pathname + fullUrl.search
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
