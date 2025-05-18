import request from "@/utils/request"

export const login = (data) => {
    return request({
        url: "/auth/login",
        method: "post",
        headers: {
            "isToken": false
        },
        data
    })
}

export const register = (data) => {
    return request({
        url: "/user/register",
        method: "post",
        headers: {
            "isToken": false
        },
        data
    })
}

export const getCaptcha = () => {
    return request({
        url: "/auth/captcha",
        method: "get",
        headers: {
            "isToken": false
        },
        responseType: "blob"
    })
}
