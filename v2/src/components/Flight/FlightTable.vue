<template>
  <el-table :data="data">
    <el-table-column prop="flightId" label="航班号" width="80"></el-table-column>
    <el-table-column prop="planTime" label="计划起飞时间" width="200"></el-table-column>
    <el-table-column prop="estimatedTime" label="预计到达时间" width="200"></el-table-column>
    <el-table-column prop="actualTime" label="实际起飞时间" width="200"></el-table-column>
    <el-table-column prop="actualArrived" label="实际到达时间" width="200"></el-table-column>
    <el-table-column prop="departure" label="出发地" width="200"></el-table-column>
    <el-table-column prop="destination" label="目的地" width="200"></el-table-column>
    <el-table-column prop="delay" label="是否延误" width="80"></el-table-column>
    <el-table-column prop="isCompleted" label="航班状态" width="100">
      <template slot-scope="scope">
        <span v-if="scope.row.isCompleted === '已完成'" style="color: green"><i class="el-icon-success"></i>{{ scope.row.isCompleted }}</span>
        <span v-else-if="scope.row.isCompleted === '在途中'" style="color: orange"><i class="el-icon-warning"></i>{{ scope.row.isCompleted }}</span>
        <span v-else-if="scope.row.isCompleted === '未开始'" style="color: red"><i class="el-icon-error"></i>{{ scope.row.isCompleted }}</span>
        <span v-else>未知</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="180">
      <template slot-scope="scope">
        <el-button type="primary" size="small" icon="el-icon-edit" @click="$emit('edit', scope.row)">修改</el-button>
        <el-button type="danger" icon="el-icon-delete" size="small" @click="$emit('delete', scope.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "FlightTable",
  props: {
    data: Array,
  },
};
</script>
