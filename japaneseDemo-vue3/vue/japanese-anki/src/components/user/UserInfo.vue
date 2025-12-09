<template>
    <!-- 显示个人信息 -->
    <div class="user-info-box">
        <h3>个人信息</h3>
        <p>这里是您的个人信息展示区域。</p>
        <a href="#" @click.prevent="openEdit">点这里修改数据</a>
        <div class="info-inner-box">            
            <div>性别</div>
            <div>{{ userInfo.gender ?? '未知'}}</div>
        </div>
        <div class="info-inner-box">
            <div for="avatar">头像</div>
            <div><img :src="userInfo.avatar ?? null" alt="头像" /></div>
        </div>
        <div class="info-inner-box">
            <div for="nickname">昵称</div>
            <div>{{ userInfo.nickname ?? '未知'}}</div>
        </div>
        <div class="info-inner-box">
            <div for="birthday">生日</div>
            <div>{{ userInfo.birthday ?? '未知'}}</div>
        </div>
        <div class="info-inner-box">
            <div for="languageLevel">语言水平</div>
            <div>{{ userInfo.languageLevel ?? 'N5'}}</div>
        </div>
        <div class="info-inner-box">
            <div for="dailyTarget">每日目标</div>
            <div>{{ userInfo.dailyTarget ?? 0}} 词</div>
        </div>
        <div class="info-inner-box">
            <div for="knownWordCount">已掌握单词数</div>
            <div>{{ userInfo.knownWordCount ?? 0}} 词</div>
        </div>
    </div>

    <!-- 弹窗 -->
    <div v-if="isEditing" class="modal-overlay">
        <div class="modal">
            <h4>修改信息</h4>
            <label>
                <span>性别：</span>
                <select v-model="userInfo.gender">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </label>
            <label>
                <span>昵称</span>
                <input v-model="userInfo.nickname" />
            </label>
            <label>
                <span>生日</span>
                <input type="date" v-model="userInfo.birthday" />
            </label>
            <label>
                <span>语言水平：</span>
                <select v-model="userInfo.languageLevel">
                    <option value="N1">N1</option>
                    <option value="N2">N2</option>
                    <option value="N3">N3</option>
                    <option value="N4">N4</option>
                    <option value="N5">N5</option>
                </select>
            </label>
            <label>
                <span>每日目标</span>
                <input type="number" v-model="userInfo.dailyTarget" />
            </label>
            <button @click="saveChanges">保存</button>
            <button @click="cancelEdit">取消</button>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { onMounted, ref } from 'vue'
    import request from '../../utils/request.ts'

    const userInfo = ref({
        gender: '',
        avatar: '',
        nickname: '',
        birthday: '',
        languageLevel: '',
        dailyTarget: 0,
        knownWordCount: 0
    })
    const isEditing = ref(false)

    onMounted(() => {
        request({
            method: 'GET',
            url: '/user/get-current-user',
        }).then(response => {
            if(response.data.success){
                userInfo.value = response.data.data
            }
        }).catch(error => {
            console.error('无法获取当前用户信息:', error)
        }) 
    })

    // 点击编辑
    const openEdit = () => {
        userInfo.value = { ...userInfo.value } // 拷贝当前数据到表单
        isEditing.value = true
    }

    // 保存修改
    const saveChanges = async () => {
        try {
            const response = await request({
                method: 'POST',
                url: '/user/update-user-info',
                data: userInfo.value
            })
            if (response.data.success) {
                userInfo.value = { ...userInfo.value } // 更新界面数据
                isEditing.value = false
                alert('修改成功！')
            } else {
                alert('修改失败：' + response.data.msg)
            }
        } catch (err) {
            console.error(err)
        }
    }

    // 取消修改
    const cancelEdit = () => {
        isEditing.value = false
    }
</script>

<style scoped>
    .info-inner-box {
        display: flex;
        justify-content: space-between;
        width: 300px;
        margin-bottom: 10px;
    }
    /* 修改数据的表单样式组 */
    .modal-overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,0.4);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 1000;
    }
    .modal {
        background: white;
        padding: 20px;
        border-radius: 8px;
        min-width: 300px;
    }
    .modal label {
        display: block;
        margin: 10px 0;
    }
    .modal input {
        width: 100%;
        padding: 5px;
        margin-top: 2px;
    }
    .modal button {
        margin: 10px 5px 0 0;
        padding: 5px 10px;
    }
</style>