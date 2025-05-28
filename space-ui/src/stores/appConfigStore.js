import { defineStore } from "pinia"

export const useAppConfigStore = defineStore("appConfig", {
    state: () => ({
        topNavBar: {
            visibleNumber: 5,
            show: true,
            currentMenu: null
        }
    })
})
