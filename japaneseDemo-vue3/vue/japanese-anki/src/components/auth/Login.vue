<template>
    <!-- 登录表单 -->
    <div id="loginForm">
        <h2>登录</h2>
        <div class="login-box">
            <div class="label-box">
                <label for="username">用户名：</label>
                <label for="password">密码：</label>
            </div>
            <div class="input-box">
                <input v-model="username">
                <input type="password" v-model="password">
            </div>
        </div>
        <button @click="loginUser()">登录</button>
        <p id="loginMessage"></p>
    </div>
    <RouterLink to="/sign-up">没有账号？点击注册</RouterLink>
</template>

<script lang="ts" setup>
    import {ref} from 'vue'
    import request from '../../utils/request'
    import router from '../../router'
import { RouterLink } from 'vue-router'

    const username = ref('')
    const password = ref('')

    const loginUser = async () => {
        request({
            method: 'post',
            url: 'user/login',
            data: {
                account: username.value,
                password: password.value
            }
        }).then(response => {
            const loginMessage = document.getElementById('loginMessage')
            if (response.data.success) {
                if (loginMessage) loginMessage.textContent = '登录成功！'
                localStorage.setItem('token', response.data.data)
                router.push('home')
            } else {
                if (loginMessage) loginMessage.textContent = '登录失败，请检查用户名和密码。'
            }
        }).catch(error => {
            console.error('登录请求失败:', error)
        })
    } 
</script>

<style scoped>
    body {
        font-family: Arial, sans-serif;
    }
    #loginForm, #wordsTable {
        margin: 20px;
    }
    #wordsTable th, #wordsTable td {
        padding: 10px;
        border: 1px solid #ccc;
    }
    #wordsTable {
        border-collapse: collapse;
        width: 100%;
    }
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }
    .pagination button {
        margin: 0 5px;
        padding: 5px 10px;
        cursor: pointer;
    }
    .login-box {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .label-box, .input-box {
        display: flex;
        flex-direction: column;
        margin-bottom: 10px;
    }
    label {
        margin: 10px 0;
    }
    input {
        margin: 5px 0;
        padding: 5px;
        width: 250px;
        font-size: 18px;
    }
</style>