<template>
  <h2>子组件</h2>
  <button @click="sendMsg">向父组件发送数据</button>
</template>
<script setup>
  import {defineEmits} from "vue";
  const emit = defineEmits(['get-message'])
  const sendMsg = () =>{
    emit('get-message','子组件中的数据')
  }
</script>