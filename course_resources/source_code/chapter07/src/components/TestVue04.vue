<template>
  <a :href="linkUrl">百度</a>
</template>
<script setup>
import {ref} from 'vue';
const linkUrl = ref('https://www.baidu.com/')
</script>