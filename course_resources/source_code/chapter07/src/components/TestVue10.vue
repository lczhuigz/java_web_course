<template>
  <div id="container">容器元素</div>
</template>
<script setup>
  import { onBeforeMount, onMounted } from 'vue';
  onBeforeMount(()=>{
    console.log('组件被挂载到DOM前的<div>元素：',document.getElementById("container"))
  })
  onMounted(()=>{
    console.log('组件被挂载到DOM后的<div>元素：',document.getElementById("container"))
  })
</script>