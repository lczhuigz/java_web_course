/*
import { createRouter, createWebHashHistory } from 'vue-router';
import TestVue15 from './components/TestVue15.vue';
import TestVue16 from './components/TestVue16.vue';
const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {path: '/test15',component: TestVue15},
        {path: '/test16',component: TestVue16}
    ]
})
export default router;*/
import { createRouter, createWebHashHistory } from 'vue-router';
import TestVue17 from './components/TestVue17.vue';
import TestVue18 from "./components/TestVue18.vue";
//创建路由实例
const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {path: '/test17/:id/:name',component: TestVue17},
        {path: '/test18',component: TestVue18}
    ]
})
export default router;