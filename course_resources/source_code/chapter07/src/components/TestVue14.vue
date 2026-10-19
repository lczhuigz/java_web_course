<template>
  <h1>父组件</h1>
  {{message}}
  <TestVue13 @get-message="getMessage" />
</template>
<script setup>
  import TestVue13 from './TestVue13.vue'
  import {ref} from "vue";
  const message = ref('')
  const getMessage = msg =>{
    message.value = msg
  }
</script>