<template>
    <div class="user">
        <h2>用户中心</h2>
        <p>欢迎来到用户中心！这里是您的个人信息和设置页面。</p>
        <div class="user-link-box">
            <RouterLink to="/user/info">完善个人信息</RouterLink>
            <RouterLink to="/user/security">账户安全</RouterLink>
            <RouterLink to="/home">返回首页</RouterLink>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { RouterLink } from 'vue-router'
</script>

<style scoped>
    .user-link-box {
        display: flex;
        align-items: center;
        justify-content: space-around;
    }
</style>