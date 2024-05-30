
<template>
  <el-container style="height: 900px">
    <el-aside width="200px">
      <el-menu :default-openeds="['1', '3']" @select="handleSelect">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>主页</template>
          <el-menu-item-group>
            <el-menu-item index="1-1">航班信息</el-menu-item>
            <el-menu-item index="1-2">用户管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-main>
      <div  v-loading="loading" element-loading-text="加载中">
        <div class="table">
          <div class="add">
            <el-input v-model="state" type="text" placeholder="请输入搜索内容" class="search" @blur="ret" :clearable="true" style="width: 200px"></el-input>
            <el-button @click="handleFocus" icon="el-icon-search" type="primary" >查询</el-button>
            <el-button @click="handleAddFlight" type="primary" icon="el-icon-circle-plus-outline" >添加航班信息</el-button>
          </div>
          <el-table :data="flight">
            <el-table-column type="flight" prop="flightId" label="航班号"  width="80"></el-table-column>
            <el-table-column prop="planTime" label="计划起飞时间" width="200"></el-table-column>
            <el-table-column prop="estimatedTime" label="预计到达时间" width="200"></el-table-column>
            <el-table-column prop="actualTime" label="实际起飞时间" width="200"></el-table-column>
            <el-table-column prop="actualArrived" label="实际到达时间" width="200"></el-table-column>
            <el-table-column prop="departure" label="出发地" width="200"></el-table-column>
            <el-table-column prop="destination" label="目的地" width="200"></el-table-column>
            <el-table-column prop="delay" label="是否延误" width="80"></el-table-column>
            <el-table-column prop="isCompleted" label="航班状态" width="100" >
              <template slot-scope="scope">
                <span v-if="scope.row.isCompleted === '已完成'" style="color: green" ><i class="el-icon-success"></i>{{ scope.row.isCompleted }}</span>
                <span v-else-if="scope.row.isCompleted === '在途中'" style="color: orange"><i class="el-icon-warning"></i> {{ scope.row.isCompleted }}</span>
                <span v-else-if="scope.row.isCompleted === '未开始'" style="color: red"><i class="el-icon-error"></i>{{ scope.row.isCompleted }}</span>
                <span v-else>Unknown</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="180">
              <template slot-scope="scope">
                <el-button type="primary" size="small" icon="el-icon-edit" @click="editFlight(scope.row)" >修改</el-button>
                <el-button type="danger" icon="el-icon-delete" size="small" @click="handleDeleteFlightById(scope.row.id)" >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div class="block" style="margin-top: 50px">
        <el-pagination background :current-page.sync="queryParam.currentPage" :page-size="queryParam.pageSize"
                       @loadData="loadData" layout="prev, pager, next" @next-click="handleNextClick" @prev-click="handlePrevClick"
                       :total="queryParam.totalRecords">
        </el-pagination>
      </div>
      <FlightDialog :visible.sync="dialogFormVisible" :edit="edit" :country="country" :casProps="casProps" @save="saveFlightEdit"/>
    </el-main>
  </el-container>
</template>

<script>
import FlightDialog from './FlightDialog.vue';
import { editFlight, addFlight, search } from "@/api/home";
import { selectCountry, selectCity, selectAir } from "@/api/air";
import { queryPageByFlight, deleteFlightById } from "@/api/page";
import axios from "axios";

export default {
  name: "FlightInfo",
  components: {
    FlightDialog
  },
  computed: {
    departureArr: {
      get: function () {
        return this.edit.departure.split("-");
      },
      set: function (arr) {
        let str = "";
        for (let i = 0; i < arr.length; i++) {
          str += arr[i];
          if (i !== arr.length - 1) {
            str += "-";
          }
        }
        this.edit.departure = str;
      },
    },
    destinationArr: {
      get: function () {
        return this.edit.destination.split("-");
      },
      set: function (arr) {
        let str = "";
        for (let i = 0; i < arr.length; i++) {
          str += arr[i];
          if (i !== arr.length - 1) {
            str += "-";
          }
        }
        this.edit.destination = str;
        console.log(this.edit);
      },
    },
  },
  data() {
    return {

      queryParam: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示数量
        totalRecords: 0,
      },
      edit: {
        departure: "",
        destination: "",
      },
      flight: [],
      dialogFormVisible: false,
      blog: true,
      num: 10,
      isAdd: false,
      state: "",
      loading: false,
      country: [],

      casProps: {
        value: "value",
        label: "label",
        // checkStrictly: true,
        leaf: true,
        lazy: true, // 此处必须为true
        lazyLoad: (node, resolve) => {
          if (node.data.label) this.getChildProject(node.data, resolve);
        },
      },
    };
  },

  created() {
    // this.getList();
    this.getCountry();
    this.loadData();
  },

  methods: {

  handleSelect(index) {
    if (index === '1-1') {
      this.$router.push('/home');
    } else if (index === '1-2') {
      this.$router.push('/user');
    }
  },
  handlePrevClick() {
    this.queryParam.currentPage -= 1;
    this.loadData();
  },
  handleNextClick() {
    this.queryParam.currentPage += 1;
    this.loadData();
  },
  loadData() {
    console.log(this.queryParam.currentPage);
    // 调用查询函数，传递当前页和每页显示数量
    queryPageByFlight(this.queryParam)
        .then((res) => {
          // 处理获取到的数据

          this.queryParam.totalRecords = res.data.total;
          this.flight = res.data.list;
        })
        .catch((error) => {
          console.error("获取失败", error);
        });
  },
  // querySearchAsync() { },
  handleFocus() {
    if (this.state == null || this.state === "") {

    } else {
      const flightId = this.state;
      search(flightId).then((res) => {
        console.log(res);
        this.flight = res.data;
      });
    }
  },
  ret() {
    if (this.state == null || this.state === "") {
      // this.getList();
      this.loadData()
    }
  },

  handleAddFlight() {
    this.isAdd = true;
    this.edit = {
      departure: "",
      destination: "",
    };
    this.dialogFormVisible = true;
    this.blog = false;
  },
  querySearchAsync() {
    // this.getList();
    this.loadData()
  },
  editFlight(row) {
    this.isAdd = false;
    this.blog = true;
    // let ss = row.flightId;
    this.edit = {
      // ss = row.id,
      id: row.id,
      flightId: row.flightId,
      planTime: row.planTime,
      actualTime: row.actualTime,
      estimatedTime: row.estimatedTime,
      actualArrived: row.actualArrived,
      departure: row.departure,
      destination: row.destination,
      delay: row.delay,
      isCompleted: row.isCompleted
    };
    this.dialogFormVisible = true;
  },
// <!--    //dialog确定-->
  saveFlightEdit() {
    if (!this.isAdd) {
      //修改
      editFlight(this.edit)
          .then((res) => {
            console.log(res);
            if (res.code === 200) {
              this.dialogFormVisible = false;
              this.$notify.success({
                title: "Successfully Modified",
                message: "修改成功",
                showClose: false,
              });
              this.loadData()
              // this.getList();
            } else {
              this.$notify.warning({
                title: "Fail to Edit",
                message: "修改失败",
                showClose: false,
              });
            }
          })
          .catch((res) => {
            this.$notify.error({
              title: "System Exception",
              message: "修改异常",
              showClose: false,
            });
          })
          .finally((res) => {
            this.dialogFormVisible = false;
          });
    } else {
      // console.log(this.edit.departure, this.edit.destination);
      //添加
      addFlight(this.edit)
          .then((res) => {
            console.log(res.data);
            if (res.data === 1) {
              this.dialogFormVisible = false;
              this.$notify.success({
                title: "Added Successfully",
                message: "添加成功",
                showClose: false,
              });
              this.loadData()
              // this.getList();
            } else {
              this.$notify.warning({
                title: "Add Failed",
                message: "添加失败",
                showClose: false,
              });
            }
          })
          .catch((res) => {
            this.$notify.error({
              title: "System Exception",
              message: "添加异常",
              showClose: false,
            });
          })
          .finally((res) => {
            this.dialogFormVisible = false;
          });
    }
  },
  handleDeleteFlightById(id) {
    let that = this;
    this.$confirm("此操作将永久删除该航班, 是否继续?", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
      callback: function (action, instance) {

        if (action === "confirm") {
          that.delete(id);
        }
      },
    });

  },
  delete(id) {
    this.loading = true;
    axios
        .post("/dev-api/deleteFlightById", {
          id: id,
        })

        .then((res) => {
          if (res.data.code === 200) {
            this.$notify.success({
              title: "Successfully Deleted",
              message: "删除成功",
              showClose: false,
            });
            this.loadData()
            // this.getList();
          } else {
            this.$notify.error({
              title: "Failed to Delete",
              message: "删除失败",
              showClose: false,
            });
          }
        })
        .catch((res) => {
          this.$notify.error({
            title: "System Exception",
            message: "删除异常",
            showClose: false,
          });
        })
        .finally(() => {
          this.loading = false;
        });
  },
  //级联选择器
  getCountry() {
    selectCountry().then((res) => {
      for (let i = 0; i < res.data.length; i++) {
        this.country.push({
          label: res.data[i].countryName,
          value: res.data[i].countryName,
        });
      }
    });
  },
  handleSelectCity(value) {
  },
  getChildProject(data, resolve) {
    selectCity(data.label).then((res) => {
      for (let i = 0; i < res.data.length; i++) {
        resolve([
          {
            label: res.data[i].cityName,
            value: res.data[i].cityName,
          },
        ]);
      }
    });

    selectAir(data.label).then((res) => {
      for (let i = 0; i < res.data.length; i++) {
        resolve([
          {
            label: res.data[i].airName,
            value: res.data[i].airName,
          },
        ]);
      }
    });
    resolve([]);
  },
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
  width: 500px;

}


</style>
