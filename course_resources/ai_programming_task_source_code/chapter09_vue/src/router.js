import { createRouter, createWebHashHistory } from 'vue-router';
import AddCloth from './components/AddCloth.vue'
import UpdateCloth from './components/UpdateCloth.vue';
//创建路由实例
const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {path: '/add',component: AddCloth},
        {path: '/update',component: UpdateCloth}
    ]
})
export default router