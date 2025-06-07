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
        }
    }
})
