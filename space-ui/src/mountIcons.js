import * as ElIconsVue from '@element-plus/icons-vue'

export default {
    install(app) {
        Object.keys(ElIconsVue).forEach((key) => {
            const component = ElIconsVue[key]
            app.component(key, component)
        })
    }
}
