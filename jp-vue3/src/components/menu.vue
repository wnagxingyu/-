<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu
} from "@element-plus/icons-vue";

import {useRouter} from 'vue-router'

const router=useRouter()

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const goto = (key: string) => {
  let menus = ["/members/list","/family/list","/relation/list","/things/list","/chat/list","/"]
  let index = Number(key[0])
  router.push(menus[index])
}
</script>

<template>
  <el-menu
    active-text-color="#ffd04b"
    background-color="#545c64"
    class="el-menu-vertical-demo"
    default-active="0"
    text-color="#fff"
    @open="handleOpen"
    @close="handleClose"
    @select="goto"

  >
    <el-menu-item index="0">
      <el-icon><icon-menu /></el-icon>
      <span style="font-size: 18px">成员信息管理</span>
    </el-menu-item>
    <el-menu-item index="1" >
      <el-icon><document /></el-icon>
      <span style="font-size: 18px">家族信息管理</span>
    </el-menu-item>
    <el-menu-item index="2" >
      <el-icon><document /></el-icon>
      <span style="font-size: 18px">家族成员关系管理</span>
    </el-menu-item>
    <el-menu-item index="3" >
      <el-icon><document /></el-icon>
      <span style="font-size: 18px">个别成员事迹管理</span>
    </el-menu-item>
    <el-menu-item index="4" >
      <el-icon><document /></el-icon>
      <span style="font-size: 18px">可视化家谱</span>
    </el-menu-item>
    <el-menu-item index="5" >
      <el-icon><document /></el-icon>
      <span style="font-size: 18px">退出</span>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>
a {
  color: #42b983;
}

label {
  margin: 0 0.5em;
  font-weight: bold;
}

code {
  background-color: #eee;
  padding: 2px 4px;
  border-radius: 4px;
  color: #304455;
}
</style>
