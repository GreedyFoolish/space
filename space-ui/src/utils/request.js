import axios from "axios"
import {getToken} from "./auth"
import {buildGetUrl, calculateByteLength, checkRepeatSubmit} from "@/utils/requestUtils.js"

// 创建 axios 实例
const service = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_API,
    timeout: 5000,
    headers: {"Content-Type": "application/json;charset=utf-8"}
})

// 防重复提交限制配置
const REPEAT_SUBMIT_INTERVAL = 1000 // 1000 毫秒
const REQUEST_SIZE_LIMIT = 5 * 1024 * 1024 // 5MB

// 请求拦截器
service.interceptors.request.use(
    config => {
        const token = getToken()
        const headers = config.headers || {}
        const isToken = headers.isToken === false
        const isRepeatSubmit = headers.repeatSubmit === false

        // token 认证
        if (token && !isToken) {
            config.headers["Authorization"] = `Bearer ${token}`
        }

        // 处理 GET 请求参数
        if (config.method === "get" && config.params) {
            config.url = buildGetUrl(config.baseURL, config.url, config.params)
            config.params = {}
        }

        // 防止重复提交
        if (!isRepeatSubmit && ["post", "put"].includes(config.method.toLowerCase())) {
            let requestData = config.data

            // 跳过 FormData 类型的防重检测
            if (requestData instanceof FormData) {
                return config
            }

            try {
                requestData = typeof requestData === "object" ? JSON.stringify(requestData) : requestData
            } catch (e) {
                console.error("序列化请求数据失败:", e)
                return config
            }

            const requestObj = {
                url: config.url,
                data: requestData,
                time: new Date().getTime()
            }

            // 计算请求体大小
            const requestSize = calculateByteLength(JSON.stringify(requestObj))
            if (requestSize >= REQUEST_SIZE_LIMIT) {
                console.warn(`[${config.url}]: 请求数据大小超出允许的5M限制，无法进行防重复提交验证。`)
                return config
            }

            return checkRepeatSubmit(config, requestObj, REPEAT_SUBMIT_INTERVAL)
        }

        return config
    },
    error => {
        console.error("Request Error:", error)
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
        const res = response.data
        if (res && res?.code !== 200) {
            const message = res.message || "Error"
            return Promise.reject(new Error(message))
        } else {
            return res
        }
    },
    error => {
        console.error("Response Error:", error.message)
        return Promise.reject(error)
    }
)

export default service
