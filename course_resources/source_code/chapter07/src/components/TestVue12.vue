<template>
  <h1>父组件</h1>
  <TestVue11 :parentData="msg"/>
</template>
<script setup>
  import TestVue11 from './TestVue11.vue'
  import {ref} from "vue";
  const msg = ref('父组件中的数据')
</script>