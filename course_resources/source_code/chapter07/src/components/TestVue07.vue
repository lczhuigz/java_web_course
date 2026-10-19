<template>
  请输入您的分数：
  <input type="text" v-model="score"><br><br>
  您的等级为：
  <p v-if="score >= 60">及格</p>
  <p v-else>不及格</p>
</template>
<script setup>
import {ref} from 'vue';
const score = ref('50')
</script>