import { validateNumber } from "@/utils/validate.js"

// 动画帧ID
let animationFrameId = null
// 获取HTML文档元素和body元素
const docEl = document.documentElement
const body = document.body

/**
 * 获取当前页面的滚动位置
 *
 * 此函数尝试通过不同的方法来获取页面的滚动位置，以确保在不同的浏览器和情况下都能得到正确的值
 * 首先，它会尝试使用 document.documentElement.scrollTop，这是在标准模式下的文档中获取滚动位置的推荐方法
 * 如果这不可用，它会回退到使用 document.body.scrollTop，这是在 quirks 模式和一些旧的浏览器中获取滚动位置的方法
 * 最后，如果前两种方法都不适用，它会尝试使用 document.body.parentNode.scrollTop，这是一个较少见的场景
 * 如果所有这些方法都失败了，函数将返回 0，表示页面没有滚动
 *
 * @returns {number} 页面的滚动位置，如果没有滚动则为 0
 */
function getScrollPosition() {
    // 判断是否是标准模式下的文档
    if (docEl && typeof docEl.scrollTop === "number") {
        return docEl.scrollTop
    }
    // 回退到 body.scrollTop
    if (body && typeof body.scrollTop === "number") {
        return body.scrollTop
    }
    // 最后尝试 body.parentNode（比较少见）
    const parent = body && body.parentNode
    if (parent && typeof parent.scrollTop === "number") {
        return parent.scrollTop
    }
    // 默认返回 0
    return 0
}

/**
 * 设置页面滚动位置
 * 此函数旨在兼容不同浏览器的滚动行为，通过设置HTML文档元素、body元素及其父节点的滚动位置，
 * 确保滚动到指定的位置，解决某些浏览器中直接设置body滚动位置可能无效的问题
 * @param {number} amount 滚动到的位置，表示滚动的像素值
 */
function setScrollPosition(amount) {
    if (docEl) {
        docEl.scrollTop = amount
    }
    if (body && body.parentNode) {
        body.parentNode.scrollTop = amount
    }
    if (body) {
        body.scrollTop = amount
    }
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
 * 封装一个兼容性的 nextAnimationFrame 函数
 * 该函数用于在下一帧开始时执行传入的回调函数
 * 主要目的是兼容不同浏览器的 requestAnimationFrame 方法
 */
const nextAnimationFrame = (function() {
    // 存储 requestAnimationFrame 方法的引用
    let rafImpl = null

    /**
     * 获取 requestAnimationFrame 方法的实现
     * 如果已经缓存了实现，则直接返回
     * 否则，尝试获取不同浏览器的实现方法
     * 如果都获取失败，则使用 setTimeout 模拟实现
     */
    function getRafImplementation() {
        if (rafImpl !== null) {
            return rafImpl
        }
        if (window.requestAnimationFrame) {
            rafImpl = window.requestAnimationFrame
        } else if (window.webkitRequestAnimationFrame) {
            rafImpl = window.webkitRequestAnimationFrame
        } else if (window.mozRequestAnimationFrame) {
            rafImpl = window.mozRequestAnimationFrame
        } else {
            // 当浏览器不支持 requestAnimationFrame 时，使用 setTimeout 模拟实现
            rafImpl = function(callback) {
                if (typeof callback === "function") {
                    return window.setTimeout(callback, 1000 / 60)
                }
            }
        }
        return rafImpl
    }

    /**
     * 调用 getRafImplementation 获取 requestAnimationFrame 的实现
     * 并使用该实现来执行传入的回调函数
     * @param {Function} callback - 在下一帧执行的回调函数
     */
    return function(callback) {
        const impl = getRafImplementation()
        if (typeof callback === "function") {
            animationFrameId = impl(callback)
        }
    }
})()

/**
 * 平滑滚动到指定位置
 * @param {Object} options 配置对象
 * @param {number} options.to 目标滚动位置
 * @param {number} [options.duration=500] 滚动总时间（毫秒），默认值为 500
 * @param {number} [options.increment=20] 每次滚动的时间间隔（毫秒），默认值为 20
 * @param {Function} [options.callback] 滚动结束后的回调函数
 */
export function smoothScrollTo({ to, duration = 500, increment = 20, callback }) {
    // 参数校验
    validateNumber(to, "to")
    // 清除旧动画帧
    if (animationFrameId) {
        window.cancelAnimationFrame(animationFrameId)
        animationFrameId = null
    }
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
            nextAnimationFrame(animateScroll)
        } else {
            if (typeof callback === "function") {
                callback()
            }
        }
    }

    animateScroll()
}
