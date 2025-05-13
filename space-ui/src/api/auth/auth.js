import request from "@/utils/request"

export const login = (data) => {
    return request({
        url: "/api/auth/login",
        method: "post",
        headers: {
            "isToken": false
        },
        data
    })
}
