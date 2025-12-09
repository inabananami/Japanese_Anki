<template>
    <!-- 模糊查询单词 -->
        <div>
            <input type="text" v-model="keyWord" placeholder="可输入日语，中文">
            <button @click="searchWords">搜索</button>
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
                <TransitionGroup tag="tbody" name="fade">
                    <tr v-for="word in words" :key="word.id">
                    <td>{{ word.japaneseWord }}</td>
                    <td>{{ word.chineseWord }}</td>
                    <td>{{ word.kana }}</td>
                    <td>{{ word.type }}</td>
                    <td>{{ word.exampleSentence }}</td>
                    </tr>
                </TransitionGroup>
            </table>
        </div>
</template>

<script lang="ts" setup>
    import { reactive, ref } from 'vue'
    import request from '../../utils/request'

    const keyWord = ref('')
    const words = reactive<any[]>([])

    const searchWords = () => {
        words.splice(0)
        // 这里可以添加搜索逻辑
        request({
            method: 'post',
            url: 'word/search',
            params: {
                keyWord: keyWord.value
            }  
        }).then(response => {
            if(response.data.success){
                response.data.data.forEach((word: { 
                    japaneseWord: string; 
                    chineseWord: string; 
                    romaji: string; 
                    kana: string; 
                    type: string;
                    exampleSentence: string; 
                }) => {
                    words.push(word)
                })
            }else if(response.data.code !== 403) {
                alert(`${response.data.msg}`)
            }
        }).catch(error => {
            console.error('搜索单词失败:', error)
        })
    }
</script>

<style scoped>
    table {
        border: 1px solid #ccc;
        border-collapse: collapse;
        margin: 20px;
    }
    th, td {
        padding: 10px;
        border: 1px solid #ccc;
        border-spacing: 0;
    }
    #wordList {
        border: 1px solid #ccc;
    }
    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.1s ease
    }
    .fade-enter-from, .fade-leave-to {
        opacity: 0.4;
    }
    .fade-enter-to, .fade-leave-from {
        opacity: 1;
    }
</style>