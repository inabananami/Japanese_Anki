<template>
    <div class="app">
        <Transition name="fade" mode="out-in">
            <RouterView />
        </Transition>
    </div>
</template>

<script setup lang="ts">
    import {RouterView} from 'vue-router'
    import { Transition } from 'vue';
</script>

<style scoped>
    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.3s ease
    }
    .fade-enter-from, .fade-leave-to {
        opacity: 0.5;
    }
    .fade-enter-to, .fade-leave-from {
        opacity: 1;
    }
</style>