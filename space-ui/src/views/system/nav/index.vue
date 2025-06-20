<template>
    <div class="app-container">
        <el-form ref="queryFormRef" :model="queryForm" :rules="queryRules" :inline="true">
            <el-form-item label="菜单名称" prop="navName">
                <el-input v-model="queryForm.navName" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="菜单状态" prop="status">
                <SelectAll
                    v-model="queryForm.status"
                    :options="statusList"
                    :invert="true"
                    :filterable="true"
                    :clearable="true"
                    :collapse-tags="true"
                    :placeholder="'请选择'"
                    :custom-style="'width: 160px'"
                ></SelectAll>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="getList">查询</el-button>
                <el-button @click="resetForm(queryFormRef)">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row class="mb20">
            <el-button type="primary" plain :icon="Plus" @click="handleAdd">新增</el-button>
            <el-button type="info" plain :icon="Sort" @click="toggleExpandAll">展开/折叠</el-button>
        </el-row>

        <el-table
            :data="tableData"
            style="width: 100%; margin-bottom: 20px"
            row-key="id"
            :default-expand-all="isExpandAll"
            :tree-props="{children: 'children'}"
            border
        >
            <el-table-column prop="navName" label="菜单名称" sortable :show-overflow-tooltip="true" width="120" />
            <el-table-column prop="navIcon" label="图标" align="center" width="80">
                <template #default="scope">
                    <component class="table-icon" :is="getIcon(scope.row.navIcon)"></component>
                </template>
            </el-table-column>
            <el-table-column prop="navUrl" label="组件路径" sortable />
            <el-table-column prop="status" label="状态" align="center">
                <template #default="scope">
                    <el-tag :type="scope.row.status === 0 ? 'success' : 'danger'">
                        {{ scope.row.status === 0 ? "启用" : "禁用" }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作栏">
                <template #default="scope">
                    <el-button type="text" size="small" :icon="Edit" @click="handleEdit( scope.row)">
                        修改
                    </el-button>
                    <el-button type="text" size="small" :icon="Delete" @click="handleDelete(scope.row)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogVisible" :title="title" width="640" append-to-body>
            <el-form ref="editFormRef" :model="editForm" :rules="editRules" label-width="100px">
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="上级菜单" prop="parentId">
                            <el-input v-model="editForm.parentId" placeholder="请输上级菜单"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="菜单类型" prop="navType">
                            <el-input v-model="editForm.navType" placeholder="请输入菜单类型"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="菜单图标" prop="navIcon">
                            <el-input v-model="editForm.navIcon" placeholder="请输入菜单图标"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" v-if="editForm.navType === 'menu'">
                        <el-form-item label="显示排序" prop="navSort">
                            <el-input v-model="editForm.navSort" placeholder="请输入显示排序"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="菜单名称" prop="navName">
                            <el-input v-model="editForm.navName" placeholder="请输入菜单名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" v-if="editForm.navType === 'menu'">
                        <el-form-item label="路由名称" prop="navName">
                            <el-input v-model="editForm.navName" placeholder="请输入路由名称"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" v-if="isMenu">
                        <el-form-item prop="isFrame">
                            <template #label>
                                <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                                    <component class="table-icon" :is="getIcon('QuestionFilled')"></component>
                                </el-tooltip>
                                是否外链
                            </template>
                            <el-radio-group v-model="editForm.isFrame">
                                <el-radio label="0">是</el-radio>
                                <el-radio label="1">否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" v-if="isMenu">
                        <el-form-item prop="navUrl">
                            <template #label>
                                <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                                    <component class="table-icon" :is="getIcon('QuestionFilled')"></component>
                                </el-tooltip>
                                路由地址
                            </template>
                            <el-input v-model="editForm.navUrl" placeholder="请输入路由地址" />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" v-if="isMenu">
                        <el-form-item prop="navComponent">
                            <template #label>
                                <el-tooltip
                                    content="访问的组件路径，如：`system/user/index`，默认在`views`目录下"
                                    placement="top"
                                >
                                    <component class="table-icon" :is="getIcon('QuestionFilled')"></component>
                                </el-tooltip>
                                组件路径
                            </template>
                            <el-input v-model="editForm.navComponent" placeholder="请输入组件路径" />
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <template #footer>
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { Delete, Edit, Plus, Sort } from "@element-plus/icons-vue"
import { ref, reactive, onMounted } from "vue"
import { getMenuList } from "@/api/system/menu.js"
import SelectAll from "@/components/SelectAll/index.vue"
import { getIcon } from "@/utils/iconUtils.js"

const statusList = [
    {
        label: "启用",
        value: true
    },
    {
        label: "禁用",
        value: false
    }
]

const queryFormRef = ref(null)
const editFormRef = ref(null)
const isExpandAll = ref(false)
const tableData = ref([])
const title = ref("新增")
const dialogVisible = ref(false)

const queryForm = reactive({
    navName: null,
    status: [true]
})
const editForm = ref({
    navName: null,
    navType: "menu",
    status: [true]
})

const isMenu = (form = editForm) => {
    return form?.navType === "menu"
}

const handleAdd = () => {
    title.value = "新增"
    dialogVisible.value = true
    resetForm(queryFormRef)
}

const toggleExpandAll = () => {
    isExpandAll.value = !isExpandAll.value
}

const handleEdit = (row) => {
    title.value = "编辑"
    dialogVisible.value = true
    editForm.value = { ...row }
}

const handleDelete = (row) => {
    console.log("handleDelete", row)
}

const validateName = (rule, value, callback) => {
    if (!value) {
        return callback(new Error("请输入用户名"))
    } else {
        return callback()
    }
}

const validateStatus = (rule, value, callback) => {
    if (value.length === 0) {
        callback(new Error("请选择菜单状态"))
    } else {
        callback()
    }
}

const queryRules = reactive({
    userName: [{ validator: validateName, trigger: ["blur", "change"] }],
    status: [{ validator: validateStatus, trigger: ["blur", "change"] }]
})

const editRules = reactive({
    userName: [{ validator: validateName, trigger: ["blur", "change"] }],
    status: [{ validator: validateStatus, trigger: ["blur", "change"] }]
})

const submitForm = () => {
    console.log("submitForm", editForm)
}

const cancel = () => {
    dialogVisible.value = false
    resetForm(editFormRef.value)
}

const resetForm = (formRef) => {
    formRef?.resetFields()
}

const getList = () => {
    queryFormRef.value.validate((valid) => {
        if (valid) {
            console.log("submitForm", queryForm)
            getMenuList(queryForm).then((res) => {
                tableData.value = res.data
            })
        }
    })
}

onMounted(() => {
    getList()
})
</script>

<style scoped>

</style>
