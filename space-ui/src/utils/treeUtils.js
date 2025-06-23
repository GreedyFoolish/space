/**
 * 将平面数据结构转换为树形结构
 * @param {Array} data - 原始数据数组
 * @param {string} id - 用作节点标识的属性名，默认为"id"
 * @param {string} parentId - 用作父节点标识的属性名，默认为"parentId"
 * @param {string} children - 用作子节点集合的属性名，默认为"children"
 * @returns {Array} - 转换后的树形结构数组
 */
export const buildTreeData = (data, id = "id", parentId = "parentId", children = "children") => {
    // 检查输入数据是否为数组，若不是则返回空数组
    if (!Array.isArray(data)) {
        return []
    }
    // 防止字段名冲突
    if (id === parentId || id === children || parentId === children) {
        throw new Error("参数 id, parentId, children 不可重复")
    }
    // 克隆并规范化原始数据（浅拷贝）
    const normalizedData = data.map(item => ({
        ...item,
        [children]: Array.isArray(item[children]) ? [...item[children]] : []
    }))
    // 使用Map存储节点id与节点对象的映射关系，便于快速查找
    const map = new Map()
    // 初始化树结构数组
    const tree = []
    // 一次遍历完成映射和树结构组装
    for (const node of normalizedData) {
        const nodeId = node[id]
        const pid = node[parentId]
        // 检查 id 是否存在
        if (nodeId === null || nodeId === undefined) {
            // 忽略无 id 节点
            continue
        }
        // 将节点添加到映射中
        map.set(nodeId, node)
        // 如果父节点id为空，则该节点为根节点
        if (pid === null || pid === undefined) {
            tree.push(node)
        } else {
            const parent = map.get(pid)
            if (parent) {
                // 如果父节点已存在，则将当前节点添加到父节点的子节点列表中
                parent[children].push(node)
            } else {
                // 父节点尚未处理，暂作为根节点加入
                tree.push(node)
            }
        }
    }
    // 返回组装完成的树形结构
    return tree
}

/**
 * 在树形结构的数据中，根据指定的键值对查找数据项
 * 如果找到了匹配的项，则返回该项，否则返回null
 *
 * @param {Array} data - 树形结构的数据数组
 * @param {*} targetValue - 要匹配的键值
 * @param {string} key - 要匹配的键名
 * @param {string} [children="children"] - 子项的键名，默认为"children"
 * @returns {Object|null} - 匹配的项或null
 */
export const findTreeNodeByKey = (data, targetValue, key = "id", children = "children") => {
    // 检查输入的数据是否有效
    if (!data || !Array.isArray(data) || data.length === 0) {
        return null
    }
    // 先检查当前层级是否有匹配项
    for (const item of data) {
        if (typeof item === "object" && item !== null) {
            if (Object.hasOwn(item, key) && item[key] === targetValue) {
                return item
            }
        }
    }
    // 再递归检查子层级
    for (const item of data) {
        if (typeof item === "object" && item !== null &&
            item[children] && Array.isArray(item[children])) {
            const result = findTreeNodeByKey(item[children], targetValue, key, children)
            if (result) {
                return result
            }
        }
    }
    // 如果没有找到匹配的项，返回null
    return null
}
