<template>
    <div v-show="!hidden" class="pagination-container">
        <el-pagination
            :background="background"
            :current-page="currentPage"
            :page-size="pageSize"
            :page-sizes="pageSizes"
            :layout="layout"
            :total="total"
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
        >
        </el-pagination>
    </div>
</template>

<script setup>
import { defineEmits } from "vue"
import { smoothScrollTo } from "@/utils/scrollUtils.js"

const props = defineProps({
    // 是否显示
    hidden: {
        type: Boolean,
        default: false
    },
    // 是否为分页按钮添加背景色
    background: {
        type: Boolean,
        default: true
    },
    // 当前页码
    currentPage: {
        type: Number,
        default: 1
    },
    // 每页显示条目个数
    pageSize: {
        type: Number,
        default: 10
    },
    // 每页显示个数选择器的选项设置
    pageSizes: {
        type: Array,
        default: () => [10, 20, 30, 50]
    },
    // 显示的分页按钮
    layout: {
        type: String,
        default: "total, sizes, prev, pager, next, jumper"
    },
    // 数据总条目数
    total: {
        type: Number,
        default: 0
    },
    // 是否自动滚动到顶部
    autoScroll: {
        type: Boolean,
        default: true
    }
})

const emits = defineEmits(["pagination", "update:currentPage", "update:pageSize"])

const scrollToTopIfNecessary = () => {
    if (props.autoScroll && window.scrollY > 0) {
        smoothScrollTo({ to: 0, duration: 800 })
    }
}

const handleCurrentChange = (value) => {
    emits("pagination", { pageIndex: value, pageSize: props.pageSize })
    emits("update:currentPage", value)
    scrollToTopIfNecessary()
}

const handleSizeChange = (value) => {
    const newPageSize = parseInt(value)
    if (isNaN(newPageSize) || newPageSize <= 0) {
        return
    }

    let newPage = props.currentPage
    // 如果当前页的数据已不存在，则跳回第一页
    if (newPage * newPageSize > props.total) {
        newPage = 1
        emits("update:currentPage", newPage)
    }

    emits("pagination", { pageIndex: newPage, pageSize: newPageSize })
    emits("update:pageSize", newPageSize)
    scrollToTopIfNecessary()
}
</script>

<style scoped>
.pagination-container {
    display: flex;
    justify-content: end;
}
</style>
