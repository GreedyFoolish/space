import {defineStore} from "pinia"
import {getToken, removeToken, setToken} from "@/utils/auth.js"

export const useUserStore = defineStore("user", {
    state: () => ({
        token: getToken(),
        name: "",
        roles: []
    }),
    getters: {
        getToken: (state) => state.token,
        getRoles: (state) => state.roles
    },
    actions: {
        login(token) {
            return new Promise((resolve, reject) => {
                this.token = token
                setToken(token)
                resolve()
            })
        },
        logout() {
            this.token = ""
            this.roles = []
            removeToken()
        }
    }
})
