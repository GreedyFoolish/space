import {createPinia} from "pinia"
import {createApp} from "vue"
import "./style.css"
import "./permission.js"
import mountIcons from '@/mountIcons.js'
import router from "@/router/index.js"
import App from "./App.vue"

createApp(App)
    .use(router)
    .use(createPinia())
    .use(mountIcons)
    .mount("#app")
