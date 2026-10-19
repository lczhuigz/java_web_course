<!--
<!DOCTYPE html>
<html lang="">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-
            scale=1.0">
  <link rel="icon" href="<%= BASE_URL %>favicon.ico">
  <title><%= htmlWebpackPlugin.options.title %></title>
</head>
<body>
<noscript>
  <strong>We're sorry but <%= htmlWebpackPlugin.options.title %>
    doesn't work properly without JavaScript enabled. Please
    enable it to continue.</strong>
</noscript>
<div id="app"></div>
&lt;!&ndash; built files will be auto injected &ndash;&gt;
</body>
</html>-->
<!--<template>
  <h1>中国风貌</h1>
  <router-link to="/test15">经济</router-link> &nbsp;
  <router-link to="/test16">教育</router-link>
  <hr>
  <router-view></router-view>
</template>-->
<template>
  <h1>路由传参</h1>
  <router-link to="/test17/1/张三">获取动态路径参数值</router-link> &nbsp;
  <router-link to="/test18?id=2&name=李四">获取查询参数值</router-link>
  <hr>
  <router-view></router-view>
</template>