import request from "@/utils/request"

export const getRouterList = (data) => {
    return request({
        url: "/api/system/routerList",
        method: "get"
    })
}
