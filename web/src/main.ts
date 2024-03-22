import { createApp } from 'vue'
import ant from 'ant-design-vue'
import App from './App.vue'
import 'ant-design-vue/dist/antd.css'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).mount('#app')
App.use(ant);
App.mount('#app');