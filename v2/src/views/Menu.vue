<template>
  <el-aside class="aside" width="200px">
    <el-menu :default-openeds="['1', '3']" @select="handleSelect">
      <el-submenu index="1">
        <template v-slot:title><i class="el-icon-message"></i>主页</template>
        <el-menu-item-group>
          <el-menu-item index="1-1">航班信息</el-menu-item>
          <el-menu-item index="1-2">用户管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
    <div class="ip" @click="fetchIP">当前ip：{{ ip }}</div>
  </el-aside>
</template>

<script>
import axios from 'axios';
export default {
  data(){
    return{
      ip:"未获取"
    }
  },
  methods: {
    handleSelect(index) {
      if (index === '1-1') {
        this.$router.push('/index/FlightInfo');
      } else if (index === '1-2') {
        this.$router.push('/index/UserInfo');
      }

    },
    async fetchIP() {
      try {
        const response = await axios.get('/dev-api/api/user-info');
        this.ip = response.data.ipAddress;
      } catch (error) {
        console.error('Error fetching IP:', error);
        this.ip = '获取失败';
      }
    },
  },
  mounted(){
    this.fetchIP()
  }
};
</script>

<style scoped>
.aside {
  background-color: #f5fafa;
  position: fixed;
  height: 100%;
  left: 0;
  top: 0;
  z-index: 1000;
}
.ip{
  margin-top: 750px;
  margin-left: 25px;

}
</style>
