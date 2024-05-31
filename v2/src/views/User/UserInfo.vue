<!--<template>-->
<!--  <el-main>-->
<!--        <div class="User_table">-->
<!--          <el-table :data="User">-->
<!--            <el-table-column prop="username" label="用户名" width="200"></el-table-column>-->
<!--            <el-table-column prop="account" label="账户地址" width="200"></el-table-column>-->
<!--            <el-table-column prop="level" label="用户等级" width="200"></el-table-column>-->
<!--            <el-table-column label="操作" width="180">-->
<!--              <template slot-scope="scope">-->
<!--                <el-button type="primary" size="small" icon="el-icon-edit" @click="editFlight(scope.row)">修改-->
<!--                </el-button>-->
<!--                <el-button type="danger" icon="el-icon-delete" size="small"-->
<!--                           @click="handleDeleteFlightById(scope.row.id)">删除-->
<!--                </el-button>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </div>-->

<!--      <div class="Userblock" style="margin-top: 50px">-->
<!--        <el-pagination background :current-page.sync="queryParam.currentPage" :page-size="queryParam.pageSize"-->
<!--                       @loadData="loadData" layout="prev, pager, next" @next-click="handleNextClick"-->
<!--                       @prev-click="handlePrevClick"-->
<!--                       :total="queryParam.totalRecords">-->
<!--        </el-pagination>-->
<!--      </div>-->
<!--&lt;!&ndash;&lt;!&ndash;      <FlightDialog :visible.sync="dialogFormVisible" :edit="edit" :country="country" :casProps="casProps"&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;                    @save="saveFlightEdit"/>&ndash;&gt;-->


<!--  </el-main>-->
<!--</template>-->
<!--<script>-->
<!--export  default {-->
<!--  data(){-->
<!--    return{-->

<!--    }-->
<!--  }-->
<!--}-->

<!--</script>-->
<template>
  <el-main>
    <el-button>编辑</el-button>
  </el-main>
</template>