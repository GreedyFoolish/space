<template>
    <div ref="tagsViewContainer" class="tags-view-container">
        <ScrollPane class="tags-view-wrapper">
            <router-link
                v-for="(item, index) in visitedViews"
                :key="`tag-${item.path || index}`"
                :to="{ path: item.path, query: item.query, fullPath: item.fullPath }"
                tag="span"
                :class="{ 'tags-view-item': true, 'active': isActive(item) }"
                @contextmenu.prevent="openMenu($event, item)"
            >
                {{ item.title }}
                <span v-if="!isAffix(item)" class="tags-view-item-close" @click.prevent.stop="closeSelectedTag(item)">
                     <CloseBold class="tags-view-item-close-icon"></CloseBold>
                </span>
            </router-link>
        </ScrollPane>
        <ul :style="menuStyle" class="tags-view-contextmenu-wrapper">
            <li v-for="(item, index) in filteredContextmenuList"
                @click="handleMenuItemClick(item)"
            >
                <component class="tags-view-contextmenu-icon" :is="item.icon" :key="index"></component>
                {{ item.name }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref, computed, shallowRef, onMounted, onBeforeUnmount, watch } from "vue"
import { useRoute } from "vue-router"
import ScrollPane from "@/layout/TagsView/ScrollPane.vue"
import router from "@/router/index.js"
import { useAppConfigStore } from "@/stores/appConfigStore.js"
import { usePermissionStore } from "@/stores/permissionStore.js"
import { useTagsViewStore } from "@/stores/tagsView.js"
import { customResolvePath } from "@/utils/pathUtils.js"

const route = useRoute()
const appConfigStore = useAppConfigStore()
const permissionStore = usePermissionStore()
const tagsViewStore = useTagsViewStore()
const tagsViewContainer = ref(null)
const selectedTag = ref(null)
const menuStyle = ref({})
const iconMap = computed(() => {
    const icons = appConfigStore.global.ElIconsVue
    // 创建一个浅引用的映射对象
    return Object.entries(icons).reduce((acc, [key, value]) => {
        acc[key] = shallowRef(value)
        return acc
    }, {})
})
const contextmenuList = ref([
    {
        name: "刷新页面",
        visible: (tag) => true,
        icon: iconMap.value["Refresh"],
        handler: (tag) => {
            refreshCurrentTag(tag)
        }
    },
    {
        name: "关闭当前",
        visible: (tag) => {
            return !tag?.meta?.affix
        },
        icon: iconMap.value["Close"],
        handler: (tag) => {
            closeSelectedTag(tag)
        }
    },
    {
        name: "关闭其他",
        visible: (tag) => true,
        icon: iconMap.value["CircleClose"],
        handler: (tag) => {
            // tagsViewStore.delOthersVisitedViews(tag)
        }
    },
    {
        name: "关闭左侧",
        visible: (tag) => true,
        icon: iconMap.value["Back"],
        handler: (tag) => {
            // tagsViewStore.delOthersVisitedViews(tag)
        }
    },
    {
        name: "关闭右侧",
        visible: (tag) => true,
        icon: iconMap.value["Right"],
        handler: (tag) => {
            // tagsViewStore.delOthersVisitedViews(tag)
        }
    },
    {
        name: "关闭所有",
        visible: (tag) => true,
        icon: iconMap.value["CircleClose"],
        handler: (tag) => {
            // tagsViewStore.delAllVisitedViews()
        }
    }
])
const visitedViews = computed(() => tagsViewStore.visitedViews)
const filteredContextmenuList = computed(() => {
    return contextmenuList.value.filter(item => item.visible(selectedTag.value))
})

const isActive = (tag) => {
    return tag.path === route.path
}

const isAffix = (tag) => {
    return tag?.meta?.affix
}

// 菜单点击处理（函数节流）
let lastClickTime = 0
const handleMenuItemClick = (item) => {
    const now = Date.now()
    if (now - lastClickTime < 300) {
        return
    }
    lastClickTime = now
    item.handler(selectedTag.value)
}

// 打开菜单
const openMenu = (e, tag) => {
    const menuWidth = 100
    const containerRect = tagsViewContainer.value.getBoundingClientRect()
    const { left: offsetLeft, top: offsetTop, width: offsetWidth } = containerRect
    let left = e.clientX - offsetLeft
    let top = e.clientY - offsetTop
    if (left > offsetWidth - menuWidth) {
        left = left - menuWidth
    }

    menuStyle.value = {
        display: "block",
        left: `${left}px`,
        top: `${top}px`
    }
    selectedTag.value = tag
}

// 关闭菜单
const closeMenu = () => {
    menuStyle.value = {
        display: "none"
    }
}

// 切换到附近的标签页
const toNearView = (index) => {
    const nearView = visitedViews.value?.[index]
    if (nearView && nearView.path !== route.path) {
        router.push(nearView.path)
    } else {
        router.push("/")
    }
}

// 刷新当前标签页
const refreshCurrentTag = (tag = router.currentRoute) => {
    const currentPath = tag.path
    router.replace({
        path: `/redirect/${encodeURIComponent(currentPath)}`,
        query: { redirect: encodeURIComponent(currentPath) }
    }).then(() => {
        console.log("刷新页面")
    })
}

// 关闭选中的标签页
const closeSelectedTag = (tag) => {
    tagsViewStore.deleteVisitedView(tag).then(result => {
        const index = result ?? 0
        if (tag.path === route.path) {
            toNearView(index)
        }
        closeMenu()
    })
}

// 过滤出需要固定的标签页
const filterAffixTags = (routes, basePath = "/") => {
    let tags = []
    routes.forEach(route => {
        if (route.affix) {
            const tagPath = customResolvePath(basePath, route.path)
            tags.push({
                fullPath: tagPath,
                meta: { affix: true },
                name: route.navName,
                path: tagPath
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

// 初始化固定的标签页
const initTagsView = () => {
    const affixTags = filterAffixTags(permissionStore.topNavbarRoutes)
    affixTags.forEach(tag => {
        tagsViewStore.addVisitedView(tag)
    })
}

// 添加标签页
const addTagsView = () => {
    if (route.path) {
        tagsViewStore.addVisitedView(route)
    }
}

onMounted(() => {
    initTagsView()
    addTagsView()
    // 点击外部关闭菜单
    document.addEventListener("click", closeMenu)
})

onBeforeUnmount(() => {
    document.removeEventListener("click", closeMenu)
})

watch(() => route.path, (newValue, oldValue) => {
    addTagsView()
})
</script>

<style scoped>
.tags-view-container {
    width: 100%;
    height: var(--tags-view-container-height);
    position: relative;
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

    .tags-view-contextmenu-wrapper {
        display: none;
        position: absolute;
        top: 0;
        left: 0;
        list-style-type: var(--tags-view-contextmenu-wrapper-list-style-type);
        font-size: var(--tags-view-contextmenu-wrapper-font-size);
        color: var(--tags-view-contextmenu-wrapper-color);
        background-color: var(--tags-view-contextmenu-wrapper-background-color);
        border-radius: var(--tags-view-contextmenu-wrapper-border-radius);
        box-shadow: var(--tags-view-contextmenu-wrapper-box-shadow);
        z-index: var(--tags-view-contextmenu-wrapper-z-index);

        li {
            padding: var(--tags-view-contextmenu-li-padding);
            display: flex;
            align-items: center;
            cursor: pointer;

            &:hover {
                background-color: var(--tags-view-contextmenu-li-hover-background-color);
            }

            .tags-view-contextmenu-icon {
                width: var(--tags-view-contextmenu-li-icon-width);
                height: var(--tags-view-contextmenu-li-icon-height);
                margin-right: var(--tags-view-contextmenu-li-icon-margin-right);
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
