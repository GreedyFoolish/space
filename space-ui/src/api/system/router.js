import request from "@/utils/request"

export const getRouterList = (data) => {
    return request({
        url: "/system/routerList",
        method: "get"
    })
}
