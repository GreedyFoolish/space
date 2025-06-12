/**
 * 节流函数
 * @param {Function} fn 需要节流的函数
 * @param {number} delay 节流时间（毫秒），默认值为 500 毫秒
 * @returns {(function(...[*]): void)|*} 添加节流后的函数
 */
export function throttle(fn, delay = 500) {
    if (typeof fn !== "function") {
        console.warn("throttle 接收到非函数参数")
        return fn
    }

    let lastTime = 0

    return function(...args) {
        const now = Date.now()
        if (now - lastTime < delay) {
            console.warn("操作过于频繁，请稍后再试")
            return
        }

        try {
            fn.apply(this, args)
        } catch (error) {
            console.error("节流函数执行出错", error)
        } finally {
            lastTime = now
        }
    }
}
