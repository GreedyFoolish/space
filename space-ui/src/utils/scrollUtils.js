import { validateNumber } from "@/utils/validate.js"

// 动画帧ID
let animationFrameId = null

/**
 * 获取页面的滚动位置
 * @returns {number} 页面滚动位置
 */
function getScrollPosition() {
    const docEl = document.documentElement
    const body = document.body
    const parent = body && body.parentNode

    return docEl?.scrollTop || parent?.scrollTop || body?.scrollTop || 0
}

/**
 * 缓动函数：easeInOutQuad
 * 描述：二次方缓动函数，先加速后减速
 * @param {number} elapsedTime 已过时间
 * @param {number} startValue 起始值
 * @param {number} change 总变化量
 * @param {number} duration 总时间
 * @returns {number} 缓动后的值
 */
const easeInOutQuad = function(elapsedTime, startValue, change, duration) {
    // 参数校验
    validateNumber(elapsedTime, "elapsedTime")
    validateNumber(startValue, "startValue")
    validateNumber(change, "change")
    validateNumber(duration, "duration")
    // 总时间不能小于等于0
    if (duration <= 0) {
        throw new RangeError("总时间 duration 必须大于 0")
    }
    // 处理参数
    const normalizedT = elapsedTime / (duration / 2)
    const halfChange = change / 2
    // 二次方缓动
    if (normalizedT < 1) {
        return halfChange * normalizedT * normalizedT + startValue
    } else {
        return -halfChange * ((normalizedT - 1) * (normalizedT - 3) - 1) + startValue
    }
}

/**
 * 移动页面
 * @param {number} amount 移动的距离
 */
function setScrollPosition(amount) {
    const docEl = document.documentElement
    const body = document.body
    const parent = body && body.parentNode

    if (docEl) {
        docEl.scrollTop = amount
    }
    if (parent) {
        parent.scrollTop = amount
    }
    if (body) {
        body.scrollTop = amount
    }
}

/**
 * 获取下一个动画帧
 * @returns {function(...[*]): void} 请求动画帧函数
 */
const nextAnimationFrame = (function() {
    return window.requestAnimationFrame ||
        window.webkitRequestAnimationFrame ||
        window.mozRequestAnimationFrame ||
        function(callback) {
            window.setTimeout(callback, 1000 / 60)
        }
})()

/**
 * 滚动到指定位置
 * @param to 目标位置
 * @param duration 滚动总时间（ms），可选，默认 500
 * @param increment 动画帧间隔时间（ms），可选，默认 20
 * @param callback 回调函数
 */
export function smoothScrollTo({ to, duration = 500, increment = 20, callback }) {
    // 如果已有动画进行，先取消
    if (animationFrameId !== null) {
        cancelAnimationFrame(animationFrameId)
    }
    // 参数校验
    validateNumber(to, "to")
    // 处理参数
    const start = getScrollPosition()
    const change = to - start
    let currentTime = 0

    const animateScroll = function() {
        currentTime += increment
        const value = easeInOutQuad(currentTime, start, change, duration)
        setScrollPosition(value)
        // 判断是否结束
        if (currentTime < duration) {
            animationFrameId = nextAnimationFrame(animateScroll)
        } else {
            animationFrameId = null
            if (typeof callback === "function") {
                callback()
            }
        }
    }

    animateScroll()
}
