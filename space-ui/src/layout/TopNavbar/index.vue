<template>
    <div class="top-navbar-wrapper">
        <!-- 顶部导航栏logo -->
        <div class="top-navbar-logo">
            <el-icon>
                <Star></Star>
            </el-icon>
            <p class="top-navbar-logo-text">
                校园空间
            </p>
        </div>
        <!-- 顶部导航栏菜单 -->
        <div class="top-navbar-menu">
            <template v-for="(item, index) in topMenu.filter((_, index) => index < visibleNumber)"
                      :key="`menu-item-${item.id ||index}`"
            >
                <div class="top-navbar-menu-item" @click="activeMenu(item)"
                     :class="{ 'active': currentMenu === item.navUrl }">
                    {{ item.navName }}
                </div>
            </template>
            <el-dropdown v-if="topMenu.length > visibleNumber" class="top-navbar-menu-item">
                <div class="more-menu">
                    更多菜单
                    <el-icon class="more-icon">
                        <More></More>
                    </el-icon>
                </div>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item
                            v-for="(item, index) in topMenu.filter((_, index) => index >= visibleNumber)"
                            :key="`dropdown-menu-${item.id ||index}`" @click="activeMenu(item)"
                            :class="{ 'active': currentMenu === item.navUrl }"
                        >
                            {{ item.navName }}
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>

        <div class="top-navbar-user">
            <el-dropdown class="dropdown-menu">
                <div class="more-menu">
                    <img class="user-avatar" :src="userAvatar" alt="用户头像" />
                    <p class="user-name">{{ userStore.name }}</p>
                </div>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item v-for="(item, index) in userMenu" :key="`dropdown-menu-${index}`">
                            {{ item.navName }}
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted, onBeforeUnmount } from "vue"
import defaultAvatar from "@/assets/img/user/avatar.jpg"
import { useAppConfigStore } from "@/stores/appConfigStore.js"
import { usePermissionStore } from "@/stores/permissionStore.js"
import { useUserStore } from "@/stores/userStore.js"
import { getCssVariableValue } from "@/utils/domUtils.js"
import { isHttp } from "@/utils/validate.js"

const appConfig = useAppConfigStore()
const permissionStore = usePermissionStore()
const userStore = useUserStore()
const visibleNumber = ref(0)
const topMenu = computed(() => {
    return permissionStore.topNavbarRoutes
})
const currentMenu = ref(null)
const userAvatar = computed(() => {
    if (userStore.avatar) {
        if (isHttp(userStore.avatar)) {
            // 网络图片
            return userStore.avatar
        } else {
            // 本地图片
            return new URL(userStore.avatar, import.meta.url).href
        }
    } else if (userStore.avatar === null) {
        return defaultAvatar
    }
})
const userMenu = ref([
    {
        navName: "用户中心",
        navUrl: "/user/center",
        navIcon: "user"
    },
    {
        navName: "退出登录",
        navUrl: "/login",
        navIcon: "logout"
    }
])

if (permissionStore.topNavbarRoutes.length < 1) {
    permissionStore.generateRouteList()
}

const activeMenu = (item) => {
    currentMenu.value = item.navUrl
    appConfig.topNavBar.currentMenu = item
}

const setVisibleNumber = () => {
    let logoWidth = getCssVariableValue("--top-navbar-logo-width").value ?? 120
    let userWidth = getCssVariableValue("--top-navbar-user-width").value ?? 180
    const width = document.body.getBoundingClientRect().width - logoWidth - userWidth
    if (width <= 0) {
        visibleNumber.value = 0
    } else {
        const menuItemWidth = getCssVariableValue("--top-navbar-menu-item-width").value
        visibleNumber.value = Math.floor(width / menuItemWidth)
        visibleNumber.value = 1
    }
}

onMounted(() => {
    setVisibleNumber()
    window.addEventListener("resize", setVisibleNumber)
})

onBeforeUnmount(() => {
    window.removeEventListener("resize", setVisibleNumber)
})
</script>

<style scoped>
.top-navbar-wrapper {
    width: 100%;
    height: var(--top-navbar-height);
    display: flex;
    box-shadow: var(--top-navbar-box-shadow);
    z-index: var(--top-navbar-z-index);

    .top-navbar-logo {
        width: var(--top-navbar-logo-width);
        height: var(--top-navbar-height);
        margin-left: var(--top-navbar-logo-margin-left);
        display: flex;
        align-items: center;
        font-size: var(--top-navbar-logo-font-size);
        line-height: var(--top-navbar-height);
        color: var(--top-navbar-logo-color);

        .top-navbar-logo-text {
            margin-left: var(--top-navbar-logo-text-margin-left);
            color: var(--top-navbar-logo-color);
        }
    }

    .top-navbar-menu {
        width: calc(100% - 300px);
        height: var(--top-navbar-height);
        display: flex;

        .top-navbar-menu-item {
            width: var(--top-navbar-menu-item-width);
            height: 100%;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            font-size: var(--top-navbar-menu-item-font-size);
            line-height: var(--top-navbar-height);
            text-align: center;
            color: var(--top-navbar-menu-item-default-color);
            border-bottom: 2px solid transparent;
            cursor: pointer;

            &:hover, &.active {
                color: var(--top-navbar-menu-item-active-color);
                border-bottom: 2px solid var(--top-navbar-menu-item-active-color);
            }
        }

        .more-menu {
            display: flex;
            justify-content: center;
            align-items: center;

            .more-icon {
                margin-left: var(--top-navbar-menu-more-icon-margin-left);
            }
        }
    }

    .top-navbar-user {
        width: var(--top-navbar-user-width);
        height: var(--top-navbar-height);

        .dropdown-menu {
            width: 100%;
            height: 100%;
            display: inline-flex;
            justify-content: end;
            align-items: center;
            font-size: var(--top-navbar-user-font-size);
            line-height: var(--top-navbar-height);
            text-align: center;
            color: var(--top-navbar-menu-item-default-color);
            border-bottom: 2px solid transparent;
            cursor: pointer;

            .more-menu {
                display: flex;
                justify-content: center;
                align-items: center;

                .user-avatar {
                    width: var(--top-navbar-user-avatar-width);
                    height: var(--top-navbar-user-avatar-height);
                }

                .user-name {
                    margin-left: var(--top-navbar-user-name-margin-left);
                    margin-right: var(--top-navbar-user-name-margin-right);
                }
            }
        }
    }
}
</style>
