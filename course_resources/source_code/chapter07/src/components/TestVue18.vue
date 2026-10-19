<template>
  <h2>查询参数</h2>
  <p>学号：{{id}}   姓名：{{name}}</p>
</template>
<script setup>
  import {useRoute} from "vue-router";
  import {ref} from "vue";
  const id = ref(0)
  const name = ref("")
  const route = useRoute()
  id.value = route.query.id
  name.value = route.query.name
</script>