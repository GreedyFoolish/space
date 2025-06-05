import * as ElIconsVue from "@element-plus/icons-vue"
import { useAppConfigStore } from "@/stores/appConfigStore.js"

export default {
    install(app) {
        useAppConfigStore().global.ElIconsVue = ElIconsVue
        Object.keys(ElIconsVue).forEach((key) => {
            const component = ElIconsVue[key]
            app.component(key, component)
        })
    }
}
