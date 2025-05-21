import request from "@/utils/request"

export const getRouterList = (data) => {
    return request({
        url: "/userPermission/routerList",
        method: "get"
    })
}
