<!-- <template>
    <div class="home">
        <RouterLink to="/word">单词区</RouterLink>
        <RouterLink to="/word/search">快捷查词</RouterLink>
        <RouterLink to="/user">我的</RouterLink>
    </div>
</template>

<script lang="ts" setup>
    import { RouterLink } from 'vue-router'
</script>

<style>
    .home {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 200px;
        height: 50px;
        padding: 24px;
    }
</style> -->

<template>
  <div class="home-container">
    <RouterLink to="/word" class="home-card">
      <div class="icon">📚</div>
      <div class="title">单词区</div>
      <div class="desc">学习和复习单词</div>
    </RouterLink>

    <RouterLink to="/word/search" class="home-card">
      <div class="icon">🔍</div>
      <div class="title">快捷查词</div>
      <div class="desc">快速查找单词意思</div>
    </RouterLink>

    <RouterLink to="/user" class="home-card">
      <div class="icon">👤</div>
      <div class="title">我的</div>
      <div class="desc">查看个人信息</div>
    </RouterLink>
  </div>
</template>

<script lang="ts" setup>
import { RouterLink } from 'vue-router'
</script>

<style>
.home-container {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;
  gap: 16px;
  padding: 24px;
}

.home-card {
  width: 150px;
  height: 150px;
  background: #f0f0f0;
  border-radius: 12px;
  text-align: center;
  padding: 16px;
  transition: transform 0.2s, background 0.2s;
  text-decoration: none;
  color: #333;
}

.home-card:hover {
  transform: scale(1.05);
  background: #e0e7ff;
}

.icon {
  font-size: 36px;
  margin-bottom: 8px;
}

.title {
  font-weight: bold;
  margin-bottom: 4px;
}

.desc {
  font-size: 12px;
  color: #666;
}
</style>