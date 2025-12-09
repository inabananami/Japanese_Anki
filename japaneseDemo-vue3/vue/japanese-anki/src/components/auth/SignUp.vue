<template>
    <div class="sign-up">
        <!-- 注册表单 -->
        <h2>注册</h2>
        <div class="sign-up-box">
            <div class="label-box">
                <div class="input-box">
                    <label for="email">邮箱：</label>
                    <input type="text" v-model="email">
                </div>                
                <div class="input-box">
                    <label for="account">用户名：</label>
                    <input type="text" v-model="account">
                </div>                
                <div class="input-box">
                    <label for="password">密码：</label>
                    <input type="password" v-model="password">
                </div>
                <div class="input-box">
                    <label for="confirmPassword">确认密码：</label>
                    <input type="password" v-model="confirmPassword">
                </div>
                <div class="input-box">
                    <label for="nickname">设定一个昵称：</label>
                    <input type="text" v-model="nickname">
                </div>
            </div>
        </div>
        <button @click="SignUpUser" class="sign-up-button">注册</button>
        <div v-if="isDisplay" class="err-msg">{{ errorMessage }}</div>
    </div>
</template>

<script lang="ts" setup>
    import {ref} from 'vue'
    import request from '../../utils/request'
    import router from '../../router'

    let isDisplay = ref(false)
    const errorMessage = ref('')
    const email = ref('')
    const account = ref('')
    const password = ref('')
    const confirmPassword = ref('')
    const nickname = ref('')
    
    const SignUpUser = () => {
        request({
            method: 'post',
            url: 'user/sign-up',
            data: {
                email: email.value,
                account: account.value,
                password: password.value,
                confirmPassword: confirmPassword.value,
                nickname: nickname.value
            }
        }).then(response => {
            if(response.data.success) {
                isDisplay.value = false
                errorMessage.value = ''
                alert('注册成功！请返回登录页面进行登录。')
                router.push('/login')
            } else {
                isDisplay.value = true
                errorMessage.value = response.data.msg
            }
        }).catch(error => {
            console.error('注册请求失败:', error)
        })
    }
</script>

<style scoped>
    .label-box {
        display: flex;
        flex-direction: column;
        gap: 12px;
    } 
    .input-box {
        display: flex;
        justify-content: space-between;
        align-items: center;
        gap: 8px;
    }
    .sign-up-button {
        margin-top: 16px;
    }
    .err-msg {
        color: red;
        margin-top: 12px;
    }
</style>