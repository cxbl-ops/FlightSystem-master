<!--<template>-->
<!--  <el-main>-->
<!--    <el-container>-->
<!--      <div v-loading="loading" element-loading-text="拼命加载中">-->
<!--        <div class="User_table">-->
<!--          <div class="addUser">-->
<!--            <el-input v-model="state" type="text" placeholder="请输入搜索内容" class="search" @blur="ret"-->
<!--                      :clearable="true" style="width: 200px"></el-input>-->
<!--            <el-button @click="handleFocus" icon="el-icon-search" type="primary">查询</el-button>-->
<!--          </div>-->
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
<!--      </div>-->
<!--      <div class="block" style="margin-top: 50px">-->
<!--        <el-pagination background :current-page.sync="queryParam.currentPage" :page-size="queryParam.pageSize"-->
<!--                       @loadData="loadData" layout="prev, pager, next" @next-click="handleNextClick"-->
<!--                       @prev-click="handlePrevClick"-->
<!--                       :total="queryParam.totalRecords">-->
<!--        </el-pagination>-->
<!--      </div>-->
<!--      <FlightDialog :visible.sync="dialogFormVisible" :edit="edit" :country="country" :casProps="casProps"-->
<!--                    @save="saveFlightEdit"/>-->
<!--  </el-main>-->
<!--  </el-container>-->
<!--</template>-->
<!--<script>-->
<!--</script>-->