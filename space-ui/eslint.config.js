import js from "@eslint/js";
import prettier from "eslint-config-prettier";
import importPlugin from "eslint-plugin-import";
import vue from "eslint-plugin-vue";
import vueParser from "vue-eslint-parser"

/**
 * eslint 配置
 * 手动触发 eslint-plugin-vue 的规则：npx eslint . --ext .js,.vue --fix
 */
export default [
    {
        files: ["**/*.js"],
        ...js.configs.recommended,
        rules: {
            // JS 基础规则可在此覆盖
        },
    },

    {
        files: ["**/*.vue"],
        plugins: {
            vue,
        },
        languageOptions: {
            parser: vueParser,
            parserOptions: {
                ecmaVersion: 2021,
                sourceType: "module",
                ecmaFeatures: {
                    modules: true,
                },
            },
        },
        rules: {
            // Vue 推荐规则
            // 组件名必须以大驼峰命名
            "vue/multi-word-component-names": 0,
            // 组件属性必须设置默认值
            "vue/require-default-prop": 0,
            // 组件中未使用的组件禁止使用
            "vue/no-unused-components": 1,

            // 缩进统一为 4 个空格（Vue 模板中）
            "vue/html-indent": ["error", 4],
            // 属性在多行时每行一个
            "vue/max-attributes-per-line": [
                "error",
                {
                    singleline: 5,
                    multiline: {
                        max: 1,
                        allowFirstLine: false,
                    },
                },
            ],
            // template 标签后换行
            "vue/html-closing-bracket-newline": [
                "error",
                {
                    singleline: "never",
                    multiline: "always",
                },
            ],
            // 引号使用双引号
            "vue/html-quotes": ["error", "double"],
        },
    },

    {
        // 所有模块通用规则
        ignores: ["/node_modules/", "/dist/", "**/*.log"],
    },

    {
        // 导入排序规则
        plugins: {
            import: importPlugin,
        },
        rules: {
            "import/order": [
                "error",
                {
                    /**
                     * 按类型分组，然后按字母排序
                     *  builtin:
                     *      Node.js 内置模块 | import fs from "fs"
                     *  external:
                     *      第三方模块 / NPM 模块 | import react from "react"
                     *  internal:
                     *      应用内部模块（使用绝对路径导入） | import utils from "@/utils"
                     *  parent:
                     *      父级目录模块（相对路径） | import config from "../config"
                     *  sibling:
                     *      同级目录模块 | import component from "./component.vue"
                     *  index:
                     *      当前目录的 index 文件 | import index from "./"
                     */
                    groups: ["builtin", "external", "internal", "parent", "sibling", "index"],
                    alphabetize: {order: "asc"},
                },
            ],
        },
        settings: {
            "import/resolver": {
                alias: {
                    map: [
                        ["@", "./src"]
                    ],
                    extensions: [".js", ".vue", ".json"]
                },
                node: {
                    extensions: [".js", ".vue", ".json"],
                },
            },
        },
    },

    // 关闭与 prettier 的冲突规则
    prettier,
];
