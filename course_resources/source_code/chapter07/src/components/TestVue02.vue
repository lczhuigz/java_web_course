<template>
  {{resData}}
</template>
<script setup>
  import {ref} from 'vue'
  const resData = ref('学如逆水行舟，不进则退！')
  setTimeout(()=>{
    resData.value = '不积跬步，无以至千里！'
  },2000)
</script>