# space*ui

## 1、依赖引入顺序

### 1.1 依赖类型

在`Vue`项目中，模块引入顺序应遵循规范，以提高可读性和维护性。

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

## 2、变量定义顺序规范

在`Vue3`中，尽管没有强制性的变量定义顺序规范，但为了提高代码的可读性和维护性，开发者通常遵循一些约定俗成的最佳实践。

以下是详细的变量定义顺序规范建议，适用于使用`<script setup>`语法的`Vue3`组件。

### 2.1 导入语句（Imports）

* 首先引入所有依赖项，如`ref`,`reactive`,`computed`等。
* 按照模块来源排序：`Vue`内置`API` > 第三方库 > 本地组件/工具函数。

```javascript
import { ref, reactive, computed } from "vue"
import MyComponent from "./MyComponent.vue"
import { someUtil } from "@/utils"
```

### 2.2 props定义（Props）

* 使用`defineProps`显声明组件接收的`props`。
* 建议使用对象形式定义类型和默认值。

```javascript
const props = defineProps({
    title: {
        type: String,
        default: "Default Title"
    },
    items: {
        type: Array,
        required: true
    }
})
```

### 2.3 emits 定义（Events）

* 使用`defineEmits`明确声明组件会触发的事件。

```javascript
const emit = defineEmits(["update:title", "submit"])
```

### 2.4 普通变量声明（const,let）

* 使用`const`或`let`定义组件内部的变量。
* 按逻辑分组，优先级高的变量靠前。

```javascript
const count = 1
```

### 2.5 模板引用（Template Refs）

* 使用`ref`在模板中绑定`DOM`或子组件实例。

```javascript
const inputRef = ref()
```

### 2.6 响应式状态（Reactive State）

* 使用`ref`或`reactive`定义组件内部的状态变量。
* 按逻辑分组，优先级高的状态靠前。

```javascript
const count = ref(0)
const selectedId = ref(null)
const form = reactive({
    name: "",
    email: ""
})
```

### 2.7 计算属性（Computed Properties）

* 使用`computed`定义派生状态。
* 按逻辑分组，命名清晰。

```javascript
const fullName = computed(() => `${form.firstName} ${form.lastName}`)
```

### 2.8 方法定义（Methods / Functions）

* 定义组件中使用的函数或事件处理逻辑。
* 建议使用驼峰命名法，如`handleAdd`、`handleEdit`等。
* 按功能分组，命名清晰，动词开头。

**具体功能分组：**

1. 事件处理函数（`Event Handlers`）
    * 定义用户交互行为，如点击、输入等。
    * 命名建议以`handle`开头，例如：`handleAdd`，`handleEdit`。
2. 验证函数（`Validation Functions`）
    * 表单字段级别的验证函数，用于被规则调用。
    * 命名建议以`validate`开头，例如：`validateName`，`validateEmail`。
3. 表单验证规则（`Validation Rules`）
    * 表单字段级别的验证函数，用于被规则调用。
    * 命名建议以`validate`开头，例如：`validateName`，`validateEmail`。
4. 表单操作函数（`Form Operations`）
    * 对表单整体的操作，如重置、提交、初始化等。
    * 命名建议语义清晰，动词开头，例如：`resetForm`，`submitForm`。
5. 其他辅助函数（`Helper Functions`）
    * 非表单相关的通用工具函数。
    * 命名建议语义清晰，动词开头，例如：`getIcon`，`formatDate`。

```javascript
function handleSubmit() {
    emit("submit", form)
}

async function fetchData() {
    const res = await someApiCall()
// ...
}
```

### 2.9 生命周期钩子（Lifecycle Hooks）

* 按执行顺序排列：`onBeforeMount`，`onMounted`，`onBeforeUpdate`，`onUpdated`，`onUnmounted`等。

```javascript
onMounted(() => {
    fetchData()
})
```

### 2.10 侦听器（Watchers）

* 使用`watch`或`watchEffect`监听响应式数据的变化。

```javascript
watch(
    () => props.items,
    (newVal) => {
        updateList(newVal)
    }
)
```

### 2.11 暴露给父组件的方法或变量（Expose）

* 使用`defineExpose`显式暴露子组件的方法或属性。
* 建议使用对象形式定义暴露的属性和方法。

```javascript
const publicMethod = () => {
// ...
}

defineExpose({
    publicMethod
})
```

### 示例代码

```javascript
<script setup>
    // 1. Imports
    import {ref} from "vue";

    // 2. Props
    const props = defineProps({ /* ... */});

    // 3. Emits
    const emit = defineEmits(["submit"]);

    // 4. Const/Let
    const count = 0;

    // 5. Template Refs
    const inputRef = ref();

    // 6. Reactive State
    const form = reactive({});

    // 7. Computed
    const fullName = computed(() => "");

    // 8. Methods
    function handleSubmit() {
    emit("submit");
}

    // 9. Lifecycle
    onMounted(() => {
        // ...
    });

    // 10. Watchers
    watch(() => props.items, () => {
        // ...
    });

    // 11. Expose
    defineExpose({});

</script>
```
