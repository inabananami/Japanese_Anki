import axios from 'axios'
import router from '../router'

const request = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: {'Content-Type': 'application/json'}
})

// 请求拦截器
request.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `${token}`
    }
    return config
})

request.interceptors.response.use(response => {
        const data = response.data
        if(data.code === 403) {
            handleTokenExpired()
        }

        return response
    },
    error => {
        // 后端返回403
        if(error.response?.status === 403) {
            handleTokenExpired()
        }

        return Promise.reject(error)
    }
)

const handleTokenExpired = () => {
        alert("登录过期，请重新登录")
        
        // 清除本地token
        localStorage.removeItem('token')

        //跳转到登录页
        router.push('/login')
}

export default request