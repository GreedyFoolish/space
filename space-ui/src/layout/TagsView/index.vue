<template>
    <div class="tags-view-container">
        <ScrollPane class="tags-view-wrapper">
            <router-link
                v-for="(item, index) in tagsViewStore.visitedViews"
                :key="`tag-${item.path || index}`"
                :to="{ path: item.path, query: item.query, fullPath: item.fullPath }"
                tag="span"
                :class="{ 'tags-view-item': true, 'active': isActive(item) }"
                :style="getLinkStyle(item.name)"
            >
                {{ item.title }}
                <span v-if="item.affix" class="el-icon-close" @click.prevent.stop="closeSelectedTag(tag)" />
            </router-link>
        </ScrollPane>
    </div>
</template>

<script setup>
import { onMounted, watch } from "vue"
import { useRoute } from "vue-router"
import ScrollPane from "@/layout/TagsView/ScrollPane.vue"
import { usePermissionStore } from "@/stores/permissionStore.js"
import { useTagsViewStore } from "@/stores/tagsView.js"
import { getCssVariableValue } from "@/utils/domUtils.js"
import { customResolvePath } from "@/utils/pathUtils.js"

const route = useRoute()
const permissionStore = usePermissionStore()
const tagsViewStore = useTagsViewStore()

const getLinkStyle = (text) => {
    console.log("getLinkStyle", text)
    const itemWidth = getCssVariableValue("--tags-view-item-width").value
    console.log("itemWidth", text)
    // return `width: ${text.length * itemWidth}px;`
}

const isActive = (tag) => {
    return tag.path === route.path
}

const filterAffixTags = (routes, basePath = "/") => {
    let tags = []
    routes.forEach(route => {
        if (route.affix) {
            const tagPath = customResolvePath(basePath, route.path)
            tags.push({
                name: route.navName,
                path: tagPath,
                fullPath: tagPath
            })
        }
        if (route?.children?.length > 0) {
            const tempTags = filterAffixTags(route.children, route.path)
            if (tempTags.length > 0) {
                tags = [...tags, ...tempTags]
            }
        }
    })
    return tags
}

const initTagsView = () => {
    const affixTags = filterAffixTags(permissionStore.topNavbarRoutes)
    affixTags.forEach(tag => {
        tagsViewStore.addVisitedView(tag)
    })
    console.log("initTagsView", permissionStore.topNavbarRoutes)
    console.log("tagsViewStore", tagsViewStore.visitedViews)
}

const addTagsView = () => {
    if (route.path) {
        console.log("name", route)
        tagsViewStore.addVisitedView(route)
    }
}

onMounted(() => {
    initTagsView()
    addTagsView()
})

watch(() => route.path, (newValue, oldValue) => {
    console.log("route ", newValue, oldValue)
    addTagsView()
})
</script>

<style scoped>
.tags-view-container {
    width: 100%;
    height: var(--tags-view-container-height);
    background: var(--tags-view-container-background-color);
    border-bottom: var(--tags-view-container-border-bottom);
    box-shadow: var(--tags-view-container-box-shadow);

    .tags-view-wrapper {
        .tags-view-item {
            height: var(--tags-view-item-height);
            margin: var(--tags-view-item-margin);
            padding: var(--tags-view-item-padding);
            display: inline-block;
            position: relative;
            font-size: var(--tags-view-item-font-size);
            line-height: var(--tags-view-item-height);
            color: var(--tags-view-item-default-color);
            background-color: var(--tags-view-item-background-color);
            border: var(--tags-view-item-border);
            cursor: pointer;

            &.active {
                display: inline-block;
                color: var(--tags-view-item-active-color);
                background-color: var(--tags-view-item-active-background-color);
                border-color: var(--tags-view-item-active-border-color);

                &::before {
                    content: "";
                    width: var(--tags-view-item-active-before-width);
                    height: var(--tags-view-item-active-before-height);
                    margin-right: var(--tags-view-item-active-before-margin-right);
                    transform: var(--tags-view-item-active-before-transform-translate);
                    display: inline-block;
                    background-color: var(--tags-view-item-active-before-background-color);
                    border-radius: var(--tags-view-item-active-before-border-radius);
                }
            }
        }
    }
}

:deep(.el-scrollbar) {
    overflow: auto;
}

:deep(.el-scrollbar__wrap) {
    display: flex;
}

:deep(.el-scrollbar__view) {
    display: inline-flex;
    align-items: center;
    flex-shrink: 0;
}
</style>
