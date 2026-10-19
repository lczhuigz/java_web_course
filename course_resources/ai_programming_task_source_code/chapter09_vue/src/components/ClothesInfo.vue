<template>
  <div>
    <h1>网上衣橱</h1>
    <input type="text" placeholder="请输入服装名称" v-model="name">&nbsp;
    <button @click="select">查询</button> &nbsp;
    <button @click="add">添加</button>
    <br><br>
    <table border="1">
      <tr>
        <th>服装编号</th>
        <th>服装名称</th>
        <th>服装风格</th>
        <th>服装价格</th>
        <th colspan="2">操作</th>
      </tr>
      <tr v-for="cloth in clothesData.list" :key="cloth.id">
        <td>{{ cloth.id }}</td>
        <td>{{ cloth.name }}</td>
        <td>{{ cloth.style }}</td>
        <td>￥{{ cloth.price }}</td>
        <td><button @click="update(cloth)">修改</button></td>
        <td><button @click="del(cloth.id)">删除</button></td>
      </tr>
    </table>
    <hr>
    <router-view v-if="isShow"></router-view>
  </div>
</template>
<script setup>
import axios from "axios"
import {onMounted, reactive, ref} from "vue"
import {useRouter} from "vue-router";
const isShow = ref(false)
const clothesData = reactive({
  list: []
})
const name = ref('')
const router = useRouter()
onMounted(() => {
  axios.get('/chapter09/all').then(res => {
    clothesData.list = res.data
  }).catch(error => {
    alert(error.message)
  })
})

const add = () => {
  isShow.value = true
  router.push('/add')
}
const update = (clothParm) =>{
  isShow.value = true
  router.push({
    path:'/update',
    query:{
      id : clothParm.id,
      name : clothParm.name,
      style : clothParm.style,
      price : clothParm.price
    }
  })
}
const del = (idParam) =>{
  axios.post('/chapter09/del',{
    id : idParam
  }).then(res => {
    alert(res.data)
    window.location.reload()
  }).catch(error => {
    console.log(error)
  })
}
const select = () =>{
  axios.post('/chapter09/selectByName',{
    name : name.value
  }).then(res => {
    clothesData.list = res.data
  }).catch(error => {
    console.log(error)
  })
}
</script>

