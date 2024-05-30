<template>
    <!-- 主页 -->
    <div class="little" v-loading="loading" element-loading-text="拼命加载中">
        <h2 style="letter-spacing: 5px; font-size: 25px">航班信息</h2>
        <div class="table">
            <div class="add">
                <el-input v-model="state" placeholder="请输入搜索内容" class="search" @blur="ret" clearable="true"
                    style="width: 200px"></el-input>
                <el-button @click="handleFocus" icon="el-icon-search" type="primary" round>查询</el-button>
                <el-button @click="handleAddFlight" type="primary" icon="el-icon-circle-plus-outline" round>添加航班信息</el-button>
                <el-button @click="change" icon="el-icon-user" class="changeUser" type="info" round>切换用户</el-button>
                <el-button @click="index1" class="index" round>index</el-button>
            </div>
            <el-table :data="flight" border>
                <el-table-column type="flight" prop="flightId" label="航班号" width="80">
                </el-table-column>
                <el-table-column prop="planTime" label="计划起飞时间" width="200">
                </el-table-column>
                <el-table-column prop="estimatedTime" label="预计到达时间" width="200">
                </el-table-column>
                <el-table-column prop="actualTime" label="实际起飞时间" width="200">
                </el-table-column>
                <el-table-column prop="actualArrived" label="实际到达时间" width="200">
                </el-table-column>
                <el-table-column prop="departure" label="出发地" width="200">
                </el-table-column>
                <el-table-column prop="destination" label="目的地" width="200">
                </el-table-column>
                <el-table-column prop="delay" label="是否延误" width="80">
                </el-table-column>
                <el-table-column label="操作" width="200">
                    <template slot-scope="scope">
                        <!-- 编辑按钮的弹窗修改 -->
                        <el-button type="primary" size="small" icon="el-icon-edit" @click="editFlight(scope.row)" round>修
                            改</el-button>

                        <el-button type="danger" icon="el-icon-delete" size="small"
                            @click="handleDeleteFlightById(scope.row.id)" round>删 除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页-->
            <div class="block">
                <el-pagination background layout="prev, pager, next" :total="1000" hide-on-single-page="true">
                </el-pagination>
            </div>
        </div>
        <el-dialog :visible.sync="dialogFormVisible" width="50%" destroy-on-close="true">
            <el-form :model="edit">
                <el-row :gutter="10">
                    <el-col :span="10">
                        <el-form-item label="计划起飞时间" label-width="120px">
                            <el-date-picker format="yyyy-MM-dd hh:mm:ss" type="datetime" v-model="edit.planTime"
                                :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="预计到达时间" label-width="120px">
                            <el-date-picker format="yyyy-MM-dd hh:mm:ss" type="datetime" v-model="edit.estimatedTime"
                                :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="实际起飞时间" label-width="120px">
                            <el-date-picker v-model="edit.actualTime" type="datetime" format="yyyy-MM-dd hh:mm:ss"
                                :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
                        </el-form-item>
                        <!-- <el-col :span="12"> -->
                        <el-form-item label="实际到达时间" label-width="120px">
                            <el-date-picker v-model="edit.actualArrived" type="datetime" format="yyyy-MM-dd hh:mm:ss"
                                :value-format="'yyyy-MM-dd hh:mm:ss'" placeholder="选择日期时间"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <!-- <el-form-item label="计划起飞时间" label-width="120px">
                <el-input v-model="edit.planTime" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="预计到达时间" label-width="120px">
                <el-input v-model="edit.estimatedTime" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="实际起飞时间" label-width="120px">
                <el-input v-model="edit.actualTime" style="width: 300px"></el-input>
            </el-form-item>
            </el-col>
            <el-col :span="12">
            <el-form-item label="实际到达时间" label-width="120px">
                <el-input v-model="edit.actualArrived" style="width: 300px"></el-input>
            </el-form-item> -->
                    <el-col :span="10">
                      <el-input v-model="edit.id" type="hidden"></el-input>
                      <el-form-item label="航班号" label-width="120px">
                            <el-input id="fId" v-model="edit.flightId" style="width: 300px"></el-input>
                        </el-form-item>
                        <el-form-item label="出发地" label-width="120px">
                            <el-cascader v-model="departureArr" placeholder="请选择出发地" :options="country"
                                @change="handleSelectCity" filterable="true" clearable="true" style="width: 300px"
                                expandTrigger="click" :props="casProps" separator="-" ></el-cascader>
                        </el-form-item>
                        <el-form-item label="目的地" label-width="120px">
                            <el-cascader v-model="destinationArr" placeholder="请选择目的地" :options="country"
                                @change="handleSelectCity" filterable="true" clearable="true" style="width: 300px"
                                :props="casProps" separator="-" ></el-cascader>
                        </el-form-item>
                        <el-form-item label="是否延误" label-width="120px">
                            <el-radio v-model="edit.delay" label="是"></el-radio>
                            <el-radio v-model="edit.delay" label="否"></el-radio>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false" icon="el-icon-circle-close" type="danger" round>取 消</el-button>
                <el-button type="primary" @click="saveFlightEdit" icon="el-icon-circle-check" round>确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import axios from "axios";
import { editFlight, addFlight, search } from "@/api/home";
import { selectCountry, selectCity, selectAir } from "@/api/air";
export default {
    name: "UserManagement",
    //计算属性
    computed: {
        departureArr: {
            get: function () {
                return this.edit.departure.split("-");
            },
            set: function (arr) {
                let str = "";
                for (let i = 0; i < arr.length; i++) {
                    str += arr[i];
                    if (i != arr.length - 1) {
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
                    if (i != arr.length - 1) {
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
            edit: {
                departure: "",
                destination: "",
            },
            flight: {},
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
        this.getList();
        this.getCountry();
    },

    methods: {
        // index1() {
        //     this.$router.push("/index");
        // },
        // change() {
        //     this.$router.push("/user");
        // },
        querySearchAsync() { },
        handleFocus() {
            if (this.state == null || this.state == "") {

            } else {
                const flightId = this.state;
                search(flightId)
                    .then((res) => {
                        console.log(res);
                        this.flight = res.data;
                    });
            }

        },
        ret() {
            if (this.state == null || this.state == "") {

                this.getList();
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
            this.getList();
        },
        editFlight(row) {
            this.isAdd = false;
            this.blog = true;
            // let ss = row.flightId;
            this.edit = {
                // id: ss,
                id:row.id,
                flightId: row.flightId,
                planTime: row.planTime,
                actualTime: row.actualTime,
                estimatedTime: row.estimatedTime,
                actualArrived: row.actualArrived,
                departure: row.departure,
                destination: row.destination,
                delay: row.delay,
            };
            this.dialogFormVisible = true;
        },
        //dialog确定
        saveFlightEdit() {
            if (!this.isAdd) {
                //修改
                editFlight(this.edit)
                    .then((res) => {
                        console.log(res);
                        if (res.code === 200) {
                            this.dialogFormVisible = false;
                            this.$notify.success({
                                title:"Successfully Modified",
                                message: "修改成功",
                                showClose: false,
                            });
                            this.getList();
                        } else {
                            this.$notify.warning({
                                title:"Fail to Edit",
                                message: "修改失败",
                                showClose: false,
                            });
                        }
                    })
                    .catch((res) => {
                        this.$notify.error({
                            title:"System Exception",
                            message: "系统异常",
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
                                title:"Added Successfully",
                                message: "添加成功",
                                showClose: false,
                            });
                            this.getList();
                        } else {
                            this.$notify.warning({
                                title:"Add Failed",
                                message: "添加失败",
                                showClose: false,
                            });
                        }
                    })
                    .catch((res) => {
                        this.$notify.error({
                            title:"System Exception",
                            message: "系统异常",
                            showClose: false,
                        });
                    })
                    .finally((res) => {
                        this.dialogFormVisible = false;
                    });
            }
        },
        //获取页面
        getList() {
            this.loading = true;
            axios
                .get("/dev-api/selectFlight")
                .then((res) => {
                    if (res.data.code === 200) {
                        this.flight = res.data.data;
                    } else {
                        this.$notify.error({
                            title:"System Exception",
                            message: "系统异常",
                            showClose: false,
                        });
                    }
                })
                .catch((res) => {
                    res.$notify.error({
                        title:"Unknown Mistake",
                        message: "未知错误",
                        showClose: false,
                    });
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        //删除
        handleDeleteFlightById(id) {
            let that = this;
            this.$confirm("此操作将永久删除该信息, 是否继续?", {
                roundButton : true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
                callback: function (action, instance) {
                    // console.log(action)
                    // console.log(instance)
                    if (action == "confirm") {
                        that.delete(id);
                    }
                },
            });
            // .then(()=>{
            //     console.log("确定")

            // }).catch(()=>{
            //     console.log("取消")
            // })
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
                        title:"Successfully Deleted",
                            message: "删除成功",
                            showClose: false,
                        });
                        this.getList();
                    } else {
                        this.$notify.error({
                          title:"Failed to Delete",
                            message: "删除失败",
                            showClose: false,
                        });
                    }
                })
                .catch((res) => {
                    this.$notify.error({
                        title:"System Exception",
                        message: "系统异常",
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
        handleSelectCity(value) { },
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

<style>
.little {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

.little .table {
    justify-content: center;
    margin: auto;
    display: flex;
    flex-flow: column;
}

.add {
    margin-bottom: 10px;
    text-align: left;
}

.changeUser {
    margin-bottom: 10px;
    width: 150px;
    float: right;
}

.block {
    display: flex;
    justify-content: center;
    margin: auto;
    flex-flow: column;
    height: 50px;
}
</style>