const DEFAULT_KEY_NAME = "未知参数";
// 缓存已生成的正则表达式，避免重复计算
const regexCache = new Map()

/**
 * 获取参数名称，若为空则返回 DEFAULT_KEY_NAME
 * @param {string} key 参数名
 * @param {string} keyName 默认键名称，默认值为 DEFAULT_KEY_NAME
 * @returns {string} 规范化后的键名称或默认键名称
 */
function getParamName(key, keyName = DEFAULT_KEY_NAME) {
    if (typeof key === "string") {
        return key.trim() || keyName;
    }
    return keyName;
}

/**
 * 统一处理验证失败的情况
 * @param {string} message 错误信息
 * @param {any} value 出错的原始值
 * @param {boolean} throwError 是否抛出错误
 * @returns {boolean} 总是返回 false 表示验证失败
 */
function handleError(message, value, throwError = false) {
    if (throwError) {
        throw new TypeError(message);
    } else {
        console.warn(message, value);
        return false;
    }
}

/**
 * 从缓存中获取已存在的正则表达式
 * @param {string} pattern 正则模式
 * @returns {RegExp|undefined} 正则表达式
 */
function getCachedRegex(pattern) {
    return regexCache.get(pattern)
}

/**
 * 对字符串中的正则特殊字符进行转义，除了 * 和 ** 外的所有字符都视为字面量
 * @param {string} str 需要转义的字符串
 * @returns {string} 转义后的字符串
 */
function escapeRegExp(str) {
    // 使用 replace 替换所有非 * 字符中的正则元字符
    return str.replace(/[.*+?^${}()|[\]\\]/g, "\\$&")
}

/**
 * 判断路径是否匹配给定的模式（支持 * 和 ** 通配符）
 * @param {string} pattern 匹配模式，可能包含 `*` 和 `**` 作为通配符
 * @param {string} path 实际路径，如果与模式匹配将返回 true
 * @returns {boolean} 是否匹配
 */
export function isPathMatch(pattern, path) {
    // 输入校验
    if (typeof pattern !== "string" || typeof path !== "string") {
        return false
    }
    // 如果缓存中已有该 pattern 对应的正则表达式，则直接使用
    const cachedRegex = getCachedRegex(pattern)
    if (cachedRegex) {
        return cachedRegex.test(path)
    }
    // 构建正则表达式模式字符串
    const escapedPattern = escapeRegExp(pattern)
    const regexPattern = escapedPattern
        .replace(/\\\*/g, "*")          // 恢复 * 以便后续替换
        .replace(/(\*\*)/g, ".*")       // 替换 ** 为 .* （递归匹配任意路径段）
        .replace(/\*/g, "[^\\/]*")      // 替换 * 为 [^\\/]*
        .replace(/\//g, "\\/")         // 替换 / 为 \/
    // 构建正则表达式并缓存
    const regex = new RegExp(`^${regexPattern}$`)
    regexCache.set(pattern, regex)
    // 测试路径是否与模式匹配
    return regex.test(path)
}

/**
 * 验证给定的值是否为有效的数字
 * @param {any} value 待验证的值
 * @param {string} key 参数名称，用于错误消息中
 * @param {Object} options 可选配置对象
 * @param {boolean} [options.throwError=true] 是否抛出错误如果验证失败
 * @returns {boolean} 如果值是一个有效的数字，则返回true；否则返回false
 */
export function validateNumber(value, key, { throwError = true } = {}) {
    const paramName = getParamName(key)

    if (value === null) {
        const errorMsg = `参数 ${paramName} 不能为 null`
        handleError(errorMsg, value, throwError)
    }

    if (typeof value !== "number") {
        const errorMsg = `参数 ${paramName} 必须为数字类型`
        handleError(errorMsg, value, throwError)
    }

    if (isNaN(value)) {
        const errorMsg = `参数 ${paramName} 不能为 NaN`
        handleError(errorMsg, value, throwError)
    }

    if (!isFinite(value)) {
        const errorMsg = `参数 ${paramName} 必须为有限数值`
        handleError(errorMsg, value, throwError)
    }

    return true
}

/**
 * 判断是否为数组
 * @param {any} arg 待判断的值
 * @returns {boolean} 是否为数组
 */
export function isArray(arg) {
    return Array.isArray
        ? Array.isArray(arg)
        : Object.prototype.toString.call(arg) === "[object Array]";
}

/**
 * 验证给定的值是否为符合长度要求的数组
 * @param {any} value 待验证的值
 * @param {string} key 参数名称，用于错误消息中
 * @param {Object} options 可选配置对象
 * @param {number} [options.minLength=0] 最小长度要求
 * @param {boolean} [options.throwError=true] 是否抛出错误如果验证失败
 * @returns {boolean} 如果值是满足条件的数组，则返回 true；否则返回 false
 */
export function validateArray(value, key, { minLength = 0, throwError = true } = {}) {
    const paramName = getParamName(key)

    if (isArray(value)) {
        const errorMsg = `参数${paramName} 必须为数组类型`;
        handleError(errorMsg, value, throwError)
    }

    if (value.length < minLength) {
        const errorMsg = `参数 ${paramName} 必须为长度 >= ${minLength} 的数组`;
        return handleError(errorMsg, value, throwError);
    }

    return true;
}

/**
 * 判断value字符串是否为空
 * @param {string} value
 * @returns {Boolean}
 */
export function isEmpty(value) {
    return value == null || value === "" ||
        value === undefined || value === "undefined"
}

/**
 * 判断url是否是http或https
 * @param {string} url
 * @returns {Boolean}
 */
export function isHttp(url) {
    return url.indexOf("http://") !== -1 || url.indexOf("https://") !== -1
}

/**
 * 判断url是否为外链
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
    return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * 判断url是否为合法url
 * @param {string} url
 * @returns {Boolean}
 */
export function validURL(url) {
    const reg = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?"\\+&%$#=~_-]+))*$/
    return reg.test(url)
}

/**
 * 判断字符串是否为小写字母
 * @param {string} str
 * @returns {Boolean}
 */
export function validLowerCase(str) {
    const reg = /^[a-z]+$/
    return reg.test(str)
}

/**
 * 判断字符串是否为大写字母
 * @param {string} str
 * @returns {Boolean}
 */
export function validUpperCase(str) {
    const reg = /^[A-Z]+$/
    return reg.test(str)
}

/**
 * 判断字符串是否为字母
 * @param {string} str
 * @returns {Boolean}
 */
export function validAlphabets(str) {
    const reg = /^[A-Za-z]+$/
    return reg.test(str)
}

/**
 * 判断字符串是否为邮箱
 * @param {string} email
 * @returns {Boolean}
 */
export function validEmail(email) {
    const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    return reg.test(email)
}

/**
 * 判断字符串是否为字符串
 * @param {string} str
 * @returns {Boolean}
 */
export function isString(str) {
    return typeof str === "string" || str instanceof String
}