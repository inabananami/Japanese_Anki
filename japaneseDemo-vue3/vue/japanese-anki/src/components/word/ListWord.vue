<template>
    <!-- 单词分页显示 -->
    <Transition name="fade" mode="out-in">
        <div :key="currentPage" id="wordsTable">
            <h2>单词列表</h2>
            <table>
                <thead>
                    <tr>
                        <th>日文单词</th>
                        <th>中文意思</th>
                        <th>假名</th>
                        <th>词性</th>
                        <th>例句</th>
                    </tr>
                </thead>

                    <tbody id="wordList">
                        <tr v-for="(word, index) in words" :key="index">
                            <td>{{ word.japaneseWord }}</td>
                            <td>{{ word.chineseWord }}</td>
                            <td>{{ word.kana }}</td>
                            <td>{{ word.type }}</td>
                            <td>{{ word.exampleSentence }}</td>
                        </tr>
                    </tbody>
            </table>

            <div class="pagination">
                <button @click="DecreasePage">上一页</button>
                <button @click="IncreasePage">下一页</button>
            </div>
        </div>
    </Transition>
</template>

<script lang="ts" setup>
    import { onMounted, ref } from 'vue'
    import request from '../../utils/request'

    const currentPage = ref(1)
    const pageSize = 10
    const words = ref<any[]>([])

    const listWords = () => {
        words.value.splice(0)
        request({
            method: 'get',
            url: 'word/list',
            params: {
                pageNum: currentPage.value
            }
        }).then(response => {
            if(response.data.success){
                words.value = response.data.data
            }
            else if(response.data.code !== 403) {
                alert(`${response.data.msg}`)
                if(currentPage.value > 1) {
                    currentPage.value-- 
                }
            }
        }).catch(error => {
            console.error('获取单词列表失败:', error)
        })
    }
    const IncreasePage = () => {
        if (currentPage.value < pageSize) {
            currentPage.value++
            console.log(currentPage.value)
            listWords()
        }else {
            alert("已经是最后一页了")
        }
    }
    const DecreasePage = () => {
        if(currentPage.value == 1){
            alert("已经是第一页了")
        }else{
            currentPage.value--
            console.log(currentPage.value)
            listWords()
        }
    }
    onMounted(() => {
        currentPage.value = 1
        listWords()
    })
</script>

<style scoped>
    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.1s ease
    }
    .fade-enter-from, .fade-leave-to {
        opacity: 0.4;
    }
    .fade-enter-to, .fade-leave-from {
        opacity: 1;
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
        align-items: center;
        margin-top: 20px;
    }
    .pagination button {
        margin: 0 5px;
        padding: 5px 10px;
        cursor: pointer;
    }
</style>