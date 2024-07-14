<template>
  <el-main class="main-center">
    <div v-loading="loading" element-loading-text="加载中">
      <div class="table">
        <div class="add">
          <el-input v-model="state" type="text" placeholder="请输入搜索内容" class="search" @blur="ret"
                    :clearable="true" style="width: 200px"></el-input>
          <el-button @click="handleFocus" icon="el-icon-search" type="primary">查询</el-button>
          <el-button @click="handleAddFlight" type="primary" icon="el-icon-circle-plus-outline">添加航班信息</el-button>
        </div>
     
        <FlightTable :data="flight" @edit="editFlight" @delete="handleDeleteFlightById"/>
      </div>
    </div>
    <div class="block" style="margin-top: 50px">
      <el-pagination background :current-page.sync="queryParam.currentPage" :page-size="queryParam.pageSize"
                     @loadData="loadData" layout="prev, pager, next" @next-click="handleNextClick"
                     @prev-click="handlePrevClick" :total="queryParam.totalRecords"></el-pagination>
    </div>
    <FlightDialog :visible.sync="dialogFormVisible" :edit="edit" :country="country" :casProps="casProps"
                  @save="saveFlightEdit"/>
    <FlightDialog cas-props="" country="" edit="" visible="" />
  </el-main>
</template>

<script>
import FlightMethods from '@/services/FlightMethods.js';
import FlightTable from "@/components/Flight/FlightTable.vue"
import FlightDialog from "@/components/Flight/FlightDialog.vue"

export default {
  components: {
    // FlightTable,
    // FlightDialog
  },
  mixins: [FlightMethods],

  created() {
    this.getCountry();
    this.loadData();
  },
};
</script>

<style scoped>
.table {
  justify-content: center;
  margin: auto;
  display: flex;
  flex-flow: column;
}

.block {
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-content: center;
}

.main-center {
  margin-left: 200px; /* 留出菜单的宽度 */
  padding: 20px;
}
</style>
