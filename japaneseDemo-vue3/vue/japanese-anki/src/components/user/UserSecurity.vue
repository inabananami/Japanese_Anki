<template>
    <!-- 显示个人敏感信息 -->
    <div class="phone-email-box">
        <a href="#" @click.prevent="openEdit" class="change-data-link">点这里修改数据</a>
        <div class="info-inner-box">            
            <div>电话号码：</div>
            <div>{{ securityData.phoneNumber ?? '未知'}}</div>
        </div>        
        <div class="info-inner-box">            
            <div>邮箱：</div>
            <div>{{ securityData.email ?? '未知'}}</div>
        </div>
    </div>

    <!-- 弹窗 -->
    <div v-if="isEditing" class="modal-overlay">
        <div class="modal">
            <h4>修改信息</h4>
            <label>
                <span>电话号码：</span>
                <input type="text" v-model="securityData.phoneNumber">
            </label>
            <label>
                <span>邮箱：</span>
                <input type="text" v-model="securityData.email">
            </label>
            <button @click="saveChanges">保存</button>
            <button @click="cancelEdit">取消</button>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { onMounted,ref } from 'vue';
    import request from '../../utils/request';

    const securityData = ref({
        phoneNumber: '',
        email: ''
    })
    const isEditing = ref(false)

    onMounted(() => {
        request({
            method: 'GET',
            url: '/user/get-current-user',
            data: {}
        }).then(response => {
            if(response.data.success) {
                securityData.value = response.data.data
            }
        }).catch(error => {
            console.error('无法获取当前用户信息',error)
        })
    })

    // 点击编辑
    const openEdit = () => {
        securityData.value = {...securityData.value}
        isEditing.value = true
    }

        // 保存修改
    const saveChanges = async () => {
        try {
            const response = await request({
                method: 'POST',
                url: '/user/update-user-info',
                data: securityData.value
            })
            if (response.data.success) {
                securityData.value = { ...securityData.value } // 更新界面数据
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
    .change-data-link {
        display: block;
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