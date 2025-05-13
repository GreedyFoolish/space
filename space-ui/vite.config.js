import {defineConfig} from "vite"
import vue from "@vitejs/plugin-vue"
import path from "path"
import AutoImport from "unplugin-auto-import/vite"
import Components from "unplugin-vue-components/vite"
import {ElementPlusResolver} from "unplugin-vue-components/resolvers"

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
