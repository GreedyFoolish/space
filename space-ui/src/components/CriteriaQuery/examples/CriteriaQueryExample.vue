<template>
    <div class="app-container">
        <CriteriaQuery v-model:criteriaList="listData" @listDataChange="handleListChange">
            <template #criteria-content="{ criteriaItem }">
                <!-- 自定义内容 -->
                <el-select v-model="criteriaItem.type" placeholder="请选择类型">
                    <el-option label="字段A" value="fieldA"></el-option>
                    <el-option label="字段B" value="fieldB"></el-option>
                </el-select>
                <el-select v-model="criteriaItem.operator" placeholder="请选择操作符">
                    <el-option label="等于" value="eq"></el-option>
                    <el-option label="大于" value="gt"></el-option>
                    <el-option label="小于" value="lt"></el-option>
                </el-select>
            </template>
        </CriteriaQuery>
    </div>
</template>

<script setup>
import { ref } from "vue"
import CriteriaQuery from "@/components/CriteriaQuery/index.vue"

const listData = ref([
    {
        id: "",
        idPath: [],
        type: "text",
        operator: "",
        value: "",
        logic: "and",
        childCount: 0,
        children: []
    }
])

const handleListChange = (data) => {
    console.log('条件列表数据变更：', data)
}
</script>

<style scoped>

</style>
