<script setup lang="ts">
import Menu from "../components/menu.vue"
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-container>
        <el-aside width="200px;">
          <Menu style="height: 98vh"/>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
