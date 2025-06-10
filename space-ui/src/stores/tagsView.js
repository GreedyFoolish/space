import { defineStore } from "pinia"

export const useTagsViewStore = defineStore("tagsView", {
    state: () => ({
        visitedViews: [],
        cachedViews: []
    }),
    getters: {
        getVisitedViews: (state) => state.visitedViews,
        getCachedViews: (state) => state.cachedViews
    },
    actions: {
        boundCheck(index) {
            if (index >= this.visitedViews.length) {
                return this.visitedViews.length - 1
            }
            return index
        },
        addVisitedView(view) {
            if (this.visitedViews.some(v => v.path === view.path)) {
                console.warn(`已添加具有相同路径“${view.path}”的路由`)
            } else {
                this.visitedViews.push(
                    Object.assign({}, view, {
                        title: view.name || "未命名"
                    })
                )
            }
        },
        deleteVisitedView(view) {
            return new Promise(resolve => {
                let index = this.visitedViews.findIndex(item => item.path === view.path)
                this.visitedViews = this.visitedViews.filter(item => {
                    return item.meta.affix || item.path !== view.path
                })
                resolve(this.boundCheck(index))
            })
        }
    }
})
