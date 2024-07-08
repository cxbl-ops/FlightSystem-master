
import FlightTable from "@/components/Flight/FlightTable.vue";
import FlightDialog from "@/components/Flight/FlightDialog.vue";


export default {
    name: "FlightInfo",
    components: {
        FlightTable,
        FlightDialog
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
    }
}
