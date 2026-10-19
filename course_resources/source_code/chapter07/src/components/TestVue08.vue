<template>
  <p v-if="flag">v-if控制的元素</p>
  <p v-show="flag">v-show控制的元素</p>
  <button @click="flag = !flag">显示/隐藏</button>
</template>
<script setup>
  import {ref} from 'vue';
  const flag = ref(true)
</script>