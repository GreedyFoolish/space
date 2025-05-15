import path from "path"
import vue from "@vitejs/plugin-vue"
import AutoImport from "unplugin-auto-import/vite"
import ElementPlusPlugin from "unplugin-element-plus/vite"
import {ElementPlusResolver} from "unplugin-vue-components/resolvers"
import Components from "unplugin-vue-components/vite"
import {defineConfig} from "vite"

// https://vite.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver()],
        }),
        ElementPlusPlugin()
    ],
    resolve: {
        alias: {
            "@": path.resolve(__dirname, "./src")
        }
    },
    server: {
        cors: true,
        host: true,
        port: 3000,
        // open: true,
        proxy: {
            "/api": {
                target: "http://127.0.0.1:8080",
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, "/api")
            },
        },
    },
})
