<template>
    <div class="top-navbar-wrapper">
        <div class="top-navbar-logo">
            <el-icon>
                <Star></Star>
            </el-icon>
            <p class="top-navbar-logo-text">
                校园空间
            </p>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.top-navbar-wrapper {
    width: 100%;

    .top-navbar-logo {
        width: 120px;
        height: 50px;
        margin-left: 20px;
        display: flex;
        align-items: center;
        font-size: 20px;
        line-height: 50px;
        color: #cc8f14;

        .top-navbar-logo-text {
            margin-left: 6px;
        }
    }
}
</style>
