<template>
  基于数组arr渲染列表：
  <div v-for="(item, index) in arr" :key="index">
    索引：{{ index }} -> 元素值：{{item}}
  </div> <br>
  基于对象user渲染列表：
  <div v-for="(value, key) in user" :key=key>
    属性名：{{ key }} -> 属性值：{{value}}
  </div>
</template>
<script setup>
  import {reactive} from 'vue';
  const arr = reactive(['北京','上海','深圳'])
  const user = reactive({id : 1, name : '张三', age : 18})
</script>