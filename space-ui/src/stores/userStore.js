import { defineStore } from "pinia"
import { usePermissionStore } from "@/stores/permissionStore.js"
import { getToken, removeToken, setToken } from "@/utils/auth.js"

export const useUserStore = defineStore("user", {
    state: () => ({
        token: getToken(),
        roles: [],
        name: "root",
        avatar: "../../assets/img/user/avatar.jpg"
    }),
    getters: {
        getToken: (state) => state.token,
        getRoles: (state) => state.roles,
        getName: (state) => state.name,
        getAvatar: (state) => state.avatar
    },
    actions: {
        login(token, role) {
            return new Promise((resolve, reject) => {
                this.token = token
                setToken(token)
                this.roles = role
                usePermissionStore().generateRouteList().then(() => {
                    resolve()
                })
            })
        },
        logout() {
            this.token = ""
            this.roles = []
            removeToken()
        }
    }
})
