<template>
  <h2>子组件</h2>
  {{parentData}}
</template>
<script setup>
  import {defineProps} from 'vue'
  defineProps({
    parentData : String
  })
</script>