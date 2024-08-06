import { addFlight, ApieditFlight , search,  ApideleteFlightById} from '@/api/Flight';
import  {selectCountry, selectCity, selectAir } from '@/api/Air';
import {queryPageByFlight} from '@/api/Page';
import FlightMixins from "@/mixins/FlightMixins";
export  default {
    mixins : [FlightMixins],
    methods:{
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
                ApieditFlight(this.edit)
                    .then((res) => {
                        console.log(res);
                        if (res.code === 200) {
                            this.dialogFormVisible = false;
                            this.$message.success({
                                title: "Successfully Modified",
                                message: "修改成功",
                                showClose: false,
                            });
                            this.loadData()
                            // this.getList();
                        } else {
                            this.$message.warning({
                                title: "Fail to Edit",
                                message: "修改失败",
                                showClose: false,
                            });
                        }
                    })
                    .catch((res) => {
                        this.$message.error({
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
                            this.$message.success({
                                title: "Added Successfully",
                                message: "添加成功",
                                showClose: false,
                            });
                            this.loadData()
                            // this.getList();
                        } else {
                            this.$message.warning({
                                title: "Add Failed",
                                message: "添加失败",
                                showClose: false,
                            });
                        }
                    })
                    .catch((res) => {
                        this.$message.error({
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
           ApideleteFlightById(id)

                .then((res) => {
                    if (res.code === 200) {
                        this.$message.success({
                            title: "Successfully Deleted",
                            message: "删除成功",
                            showClose: false,
                        });
                        this.loadData()
                        // this.getList();
                    } else {
                        this.$message.error({
                            title: "Failed to Delete",
                            message: "删除失败",
                            showClose: false,
                        });
                    }
                })
                .catch((res) => {
                    this.$message.error({
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

    }
}

