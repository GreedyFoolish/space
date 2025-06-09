<template>
    <div class="tags-view-container">
        <ScrollPane class="tags-view-wrapper">
            <router-link
                v-for="(item, index) in tagsViewStore.visitedViews"
                :key="`tag-${item.path || index}`"
                :to="{ path: item.path, query: item.query, fullPath: item.fullPath }"
                tag="span"
                :class="{ 'tags-view-item': true, 'active': isActive(item) }"
            >
                {{ item.title }}
                <span v-if="!isAffix(item)" class="tags-view-item-close">
                     <CloseBold class="tags-view-item-close-icon"></CloseBold>
                </span>
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
import { customResolvePath } from "@/utils/pathUtils.js"

const route = useRoute()
const permissionStore = usePermissionStore()
const tagsViewStore = useTagsViewStore()

const isActive = (tag) => {
    return tag.path === route.path
}

const isAffix = (tag) => {
    return tag?.meta?.affix
}

const filterAffixTags = (routes, basePath = "/") => {
    let tags = []
    routes.forEach(route => {
        if (route.affix) {
            const tagPath = customResolvePath(basePath, route.path)
            tags.push({
                fullPath: tagPath,
                meta: { affix: true },
                name: route.navName,
                path: tagPath,
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
}

const addTagsView = () => {
    if (route.path) {
        tagsViewStore.addVisitedView(route)
    }
}

onMounted(() => {
    initTagsView()
    addTagsView()
})

watch(() => route.path, (newValue, oldValue) => {
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
            display: flex;
            align-items: center;
            position: relative;
            font-size: var(--tags-view-item-font-size);
            line-height: var(--tags-view-item-height);
            color: var(--tags-view-item-default-color);
            background-color: var(--tags-view-item-background-color);
            border: var(--tags-view-item-border);
            cursor: pointer;

            &.active {
                color: var(--tags-view-item-active-color);
                background-color: var(--tags-view-item-active-background-color);
                border-color: var(--tags-view-item-active-border-color);

                &:before {
                    content: "";
                    width: var(--tags-view-item-active-before-width);
                    height: var(--tags-view-item-active-before-height);
                    margin: var(--tags-view-item-active-before-margin);
                    transform: var(--tags-view-item-active-before-transform-translate);
                    background-color: var(--tags-view-item-active-before-background-color);
                    border-radius: var(--tags-view-item-active-before-border-radius);
                }

                .tags-view-item-close {
                    color: var(--tags-view-item-active-color);
                }
            }

            .tags-view-item-close {
                width: var(--tags-view-item-close-width);
                height: var(--tags-view-item-close-height);
                margin-left: var(--tags-view-item-close-margin-left);
                color: var(--tags-view-item-default-color);
                border-radius: var(--tags-view-item-close-border-radius);
                transition: var(--tags-view-item-close-transition);
                transform-origin: var(--tags-view-item-close-transform-origin);

                &:hover {
                    color: var(--tags-view-item-close-hover-color);
                    background-color: var(--tags-view-item-close-hover-background-color);
                }

                .tags-view-item-close-icon {
                    transform: var(--tags-view-item-close-icon-transform);
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
