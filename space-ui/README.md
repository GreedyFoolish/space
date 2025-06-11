# space-ui

## 1、依赖引入顺序

### 1.1 依赖类型

在`Vue`项目中，模块引入顺序应遵循清晰、一致的规范，以提高可读性和维护性。

通过在`eslint.config.js`中配置`import/order`，已具备良好的排序规则支持。

```javascript
// 1. Node.js 内置模块（builtin）
import fs from "fs"
import path from "path"

// 2. 第三方模块（external），包括 vue 及其生态库
import { createApp } from "vue"
import { createStore } from "vuex"
import { createRouter, createWebHistory } from "vue-router"
import axios from "axios"
import ElementPlus from "element-plus"

// 3. 应用内部模块（internal）
import App from "@/App.vue"
import routes from "@/router/routes"
import store from "@/store/index"
import config from "@/config/app"

// 4. 父级目录模块（parent）
import BaseLayout from "../layout/BaseLayout.vue"

// 5. 同级目录模块（sibling）
import SidebarText from "./SidebarText.vue"

// 6. 当前目录 index 文件（index）
import index from "./index.vue"
```

### 1.2 vue依赖引入顺序

`vue`依赖引入顺序本身没有语法上的强制规范要求，但为了代码的可读性和团队协作一致性，建议遵循一些约定俗成的排序规范。

**推荐顺序规范（按类别分组）**

1. 编译器宏（`Compiler Macros`）
    * 如`defineProps`、`defineEmits`等，这些是`<script setup>`中特有的宏函数。
    * 应该放在最前面，因为它们是模板编译时直接依赖的。

2. 响应式`API`（`Reactivity APIs`）
    * 如`ref`、`reactive`、`computed`、`watch`等。
    * 用于创建和管理响应式状态。

3. 渲染相关`API`（`Rendering Utilities`）
    * 如`h`、`render`等。
    * 用于手动控制虚拟`DOM`的构建或自定义渲染逻辑。

```javascript
// 示例推荐顺序
import { defineProps, defineEmits, ref, reactive, computed, watch, h } from "vue"
```
