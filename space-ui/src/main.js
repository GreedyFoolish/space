import { createPinia } from "pinia"
import { createApp } from "vue"
import "@/assets/styles/index.css"
import "@/assets/styles/common.css"
import "@/permission.js"
import mountIcons from "@/mountIcons.js"
import router from "@/router/index.js"
import { useAppConfigStore } from "@/stores/appConfigStore.js"
import { setAppConfigStore } from "@/utils/iconUtils.js"
import App from "./App.vue"

createApp(App)
    .use(router)
    .use(createPinia())
    .use(mountIcons)
    .mount("#app")

setAppConfigStore(useAppConfigStore())
