<template>
  <el-dialog :visible.sync="visible" width="50%" :destroy-on-close="true">
    <el-form :model="edit">
      <el-row :gutter="10">
        <el-col :span="10">
          <el-form-item label="计划起飞时间" label-width="120px">
            <el-date-picker format="yyyy-MM-dd hh:mm:ss" type="datetime" v-model="edit.planTime"
                            :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="预计到达时间" label-width="120px">
            <el-date-picker id="estimatedTime" v-model="edit.estimatedTime" format="yyyy-MM-dd hh:mm:ss" type="datetime"
                            :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="实际起飞时间" label-width="120px">
            <el-date-picker id="actualTime" v-model="edit.actualTime" format="yyyy-MM-dd hh:mm:ss" type="datetime"
                            :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="实际到达时间" label-width="120px">
            <el-date-picker id="actualArrived" v-model="edit.actualArrived" format="yyyy-MM-dd hh:mm:ss" type="datetime"
                            :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="航班号" label-width="120px">
            <el-input v-model="edit.flightId" style="width: 300px"></el-input>
          </el-form-item>
          <el-form-item label="出发地" label-width="120px">
            <el-cascader v-model="departureArr" placeholder="请选择出发地" :options="country"
                         :filterable="true" :clearable="true" style="width: 300px" expandTrigger="click" :props="casProps"
                         separator="-"></el-cascader>
          </el-form-item>
          <el-form-item label="目的地" label-width="120px">
            <el-cascader v-model="destinationArr" placeholder="请选择目的地" :options="country"
                         :filterable="true" :clearable="true" style="width: 300px" :props="casProps" separator="-"></el-cascader>
          </el-form-item>
          <el-form-item label="是否延误" label-width="120px">
            <el-radio v-model="edit.delay" label="是"></el-radio>
            <el-radio v-model="edit.delay" label="否"></el-radio>
          </el-form-item>
          <el-form-item label="航班状态" label-width="120px">
            <el-radio v-model="edit.isCompleted" label="已完成" style="color: green"></el-radio>
            <el-radio v-model="edit.isCompleted" label="在途中" style="color: orange"></el-radio>
            <el-radio v-model="edit.isCompleted" label="未开始" style="left: 190px;top:-40px;color: red" ></el-radio>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false" icon="el-icon-circle-close" type="danger" >取 消</el-button>
      <el-button type="primary" @click="saveFlightEdit" icon="el-icon-circle-check" >确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: 'FlightDialog',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    edit: {
      type: Object,
      required: true
    },
    country: {
      type: Array,
      required: true
    },
    casProps: {
      type: Object,
      required: true
    }
  },
  computed: {
    departureArr: {
      get() {
        return this.edit.departure ? this.edit.departure.split("-") : [];
      },
      set(arr) {
        this.edit.departure = arr.join("-");
      }
    },
    destinationArr: {
      get() {
        return this.edit.destination ? this.edit.destination.split("-") : [];
      },
      set(arr) {
        this.edit.destination = arr.join("-");
      }
    }
  },
  methods: {
    saveFlightEdit() {
      this.$emit('save');
    }
  }
}
</script>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
}
</style>
