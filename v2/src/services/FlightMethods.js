import { addFlight, ApieditFlight , search,  deleteFlightById} from '@/api/Flight';
import  {selectCountry, selectCity, selectAir } from '@/api/Air';
import {queryPageByFlight} from '@/api/Page';
export function handlePrevClick() {
    this.queryParam.currentPage -= 1;
    this.loadData();
}

export function handleNextClick() {
    this.queryParam.currentPage += 1;
    this.loadData();
}

export function loadData() {
    queryPageByFlight(this.queryParam)
        .then(res => {
            this.queryParam.totalRecords = res.data.total;
            this.flight = res.data.list;
        })
        .catch(error => {
            console.error("获取失败", error);
        });
}

export function handleFocus() {
    if (this.state) {
        search(this.state).then(res => {
            this.flight = res.data;
        });
    }
}

export function ret() {
    if (!this.state) {
        this.loadData();
    }
}

export function handleAddFlight() {
    this.isAdd = true;
    this.edit = {
        departure: "",
        destination: "",
    };
    this.dialogFormVisible = true;
}

export function editFlight(row) {
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
}

export function saveFlightEdit() {
    if (this.isAdd) {
        addFlight(this.edit)
            .then(res => {
                if (res.data === 1) {
                    this.dialogFormVisible = false;
                    this.$notify.success({
                        title: "Added Successfully",
                        message: "添加成功",
                        showClose: false,
                    });
                    this.loadData();
                } else {
                    this.$notify.warning({
                        title: "Add Failed",
                        message: "添加失败",
                        showClose: false,
                    });
                }
            })
            .catch(() => {
                this.$notify.error({
                    title: "System Exception",
                    message: "添加异常",
                    showClose: false,
                });
            });
    } else {
        ApieditFlight(this.edit)
            .then(res => {
                if (res.code === 200) {
                    this.dialogFormVisible = false;
                    this.$notify.success({
                        title: "Successfully Modified",
                        message: "修改成功",
                        showClose: false,
                    });
                    this.loadData();
                } else {
                    this.$notify.warning({
                        title: "Fail to Edit",
                        message: "修改失败",
                        showClose: false,
                    });
                }
            })
            .catch(() => {
                this.$notify.error({
                    title: "System Exception",
                    message: "修改异常",
                    showClose: false,
                });
            });
    }
}

export function handleDeleteFlightById(id) {
    this.$confirm("此操作将永久删除该航班, 是否继续?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
    }).then(() => {
        this.deleteFlight(id);
    }).catch(() => {});
}

export function deleteFlight(id) {
    this.loading = true;
    deleteFlightById(id)
        .then(res => {
            if (res.data === 1) {
                this.$notify.success({
                    title: "Successfully Deleted",
                    message: "删除成功",
                    showClose: false,
                });
                this.loadData();
            } else {
                this.$notify.warning({
                    title: "Failed to Delete",
                    message: "删除失败",
                    showClose: false,
                });
            }
            this.loading = false;
        })
        .catch(() => {
            this.$notify.error({
                title: "System Exception",
                message: "删除异常",
                showClose: false,
            });
            this.loading = false;
        });
}

export function getCountry() {
    selectCountry().then(res => {
        this.country = res.data.map(item => ({
            value: item.code,
            label: item.name,
        }));
    });
}

export function getChildProject(data, resolve) {
    selectCity(data.value).then(res => {
        const cities = res.data.map(item => ({
            value: item.code,
            label: item.name,
            leaf: true,
        }));
        resolve(cities);
    });
}
