/**
 * 校验并返回解码后的 URL，若无效则返回 null
 * @param url 传入的重定向地址
 * @returns {string | null} 解码后的 URL 或 null
 */
export const getValidDecodeUrl = (url) => {
    if (!url) {
        return null
    }
    try {
        const decodedUrl = decodeURIComponent(url)
        // 相对路径认为是安全的
        if (decodedUrl.startsWith("/")) {
            return decodedUrl
        }
        // 后续可扩展更多校验规则，如白名单域名等
        return null
    } catch (e) {
        return null
    }
}
