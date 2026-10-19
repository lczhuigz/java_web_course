<template>
  <div>
    <h2>添加服装</h2>
      <input type="text" v-model="cloth.name" placeholder="服装名称"> &nbsp;
      <input type="text" v-model="cloth.style" placeholder="服装风格"> &nbsp;
      <input type="text" v-model="cloth.price" placeholder="服装价格"> &nbsp;
    &nbsp;
    <button @click="add">添加</button> &nbsp;
    <button @click="clear">清空</button>
  </div>
</template>
<script setup>
import {ref} from "vue";
import axios from "axios";

const cloth = ref({
  name: '',
  style: '',
  price:'',
})
const add = () =>{
  axios.post('/chapter09/add',{
    name : cloth.value.name,
    style : cloth.value.style,
    price : cloth.value.price
  }).then(res => {
    console.log(res.data)
    alert(res.data)
    window.location.reload()
  }).catch(error => {
    console.log(error)
  })
}
const clear = () =>{
  cloth.value.name = ref('')
  cloth.value.style = ref('')
  cloth.value.price = ref('')
}

</script>
