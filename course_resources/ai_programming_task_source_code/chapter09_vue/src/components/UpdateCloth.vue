<template>
  <div>
    <h2>修改服装</h2>
      <input type="text" v-model="cloth.name" placeholder="服装名称"> &nbsp;
      <input type="text" v-model="cloth.style" placeholder="服装风格"> &nbsp;
      <input type="text" v-model="cloth.price" placeholder="服装价格"> &nbsp;
    &nbsp;
    <button @click="update">修改</button> &nbsp;
    <button @click="clear">清空</button>
  </div>
</template>
<script setup>
import {ref, watch} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";
const route = useRoute()
let cloth = ref({
  name : route.query.name,
  style : route.query.style,
  price : route.query.price
})
watch(
    () => route.query,
    (newQuery) => {
      cloth.value.name = newQuery.name;
      cloth.value.style = newQuery.style;
      cloth.value.price = newQuery.price;
    }
);
const update = () =>{
  axios.post('/chapter09/update',{
    id : route.query.id,
    name : cloth.value.name,
    style : cloth.value.style,
    price : cloth.value.price
  }).then(res => {
    alert(res.data)
    window.location.reload()
  }).catch(error => {
    console.log(error.message)
  })
}
const clear = () =>{
  cloth.value.name = ref('')
  cloth.value.style = ref('')
  cloth.value.price = ref('')
}

</script>
