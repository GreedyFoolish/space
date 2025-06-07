/**
 * 自定义路径拼接
 * @param {string} basePath 基础路径
 * @param {string} routePath 子路径
 * @returns {* | string} 拼接后的完整路径
 */
export function customResolvePath(basePath, routePath) {
    // 去掉父路由开头和末尾的所有斜杠 /
    const safeBase = basePath ? basePath.replace(/^\/+|\/+$/g, "") : ""
    // 去掉子路由开头的所有斜杠 /
    const safeRoute = routePath ? routePath.replace(/^\/+/, "") : ""
    // 如果任意一个为空，则直接返回另一个
    if (!safeBase || !safeRoute) {
        return `/${safeBase || safeRoute}`;
    }
    // 返回拼接后的路径
    return `/${safeBase}/${safeRoute}`
}
