import { defineStore } from "pinia"

export const useAppConfigStore = defineStore("appConfig", {
    state: () => ({
        global: {
            ElIconsVue: []
        },
        topNavBar: {
            visibleNumber: 5,
            show: true,
            currentMenu: null
        },
        sideBar: {
            collapse: false
        }
    })
})
