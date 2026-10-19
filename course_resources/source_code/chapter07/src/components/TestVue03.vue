<template>
  {{obj.name}}
  {{obj.age}}
</template>
<script setup>
  import {reactive} from 'vue'
  const obj = reactive({
    name : '张三',
    age : 18
  })
  setTimeout(()=>{
    obj.name = '李四'
    obj.age = 20
  },2000)
</script>