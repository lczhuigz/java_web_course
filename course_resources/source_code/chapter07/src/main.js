/*import { createApp } from 'vue'
import App from './components/TestVue14.vue'
createApp(App).mount('#app')*/

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
const app = createApp(App);
app.use(router);
app.mount('#app');
