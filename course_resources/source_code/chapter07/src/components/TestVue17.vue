<template>
  <h2>动态路径参数</h2>
  <p>学号：{{userId}}   姓名：{{userName}}</p>
</template>
<script setup>
  import {useRoute} from "vue-router";
  import {ref} from "vue";
  const userId = ref(0)
  const userName = ref("")
  const route = useRoute()
  userId.value = route.params.id
  userName.value = route.params.name
</script>