import request from "@/utils/request"

/**
 * 获取菜单列表
 */
export const getMenuList = (data) => {
    return request({
        url: "/nav/list",
        method: "get",
        params: data
    })
}

/**
 * 新增菜单
 */
export const addMenu = (data) => {
    return request({
        url: "/nav",
        method: "post",
        data: data
    })
}

/**
 * 编辑菜单
 */
export const updateMenu = (id, data) => {
    return request({
        url: `/nav/${id}`,
        method: "put",
        data: data
    })
}

/**
 * 删除菜单
 */
export const deleteMenu = (id) => {
    return request({
        url: `/nav/${id}`,
        method: "delete"
    })
}
