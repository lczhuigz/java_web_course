<template>
  请输入姓名：<input type="text" v-model="name"> <br><br>
  {{name}}，欢迎你
</template>
<script setup>
  import {ref} from 'vue';
  const name = ref('张三')
</script>