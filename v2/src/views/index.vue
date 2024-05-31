<template>
  <el-container style="height: 900px">
    <router-view/>
    <Menu/>
  </el-container>
</template>

<script>
import Menu from '@/views/Menu.vue';
import routes from "@/router";

export default {
  components: {
    Menu,
    routes
  }
}
</script>

