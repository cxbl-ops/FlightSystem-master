import FlightDialog from "@/components/Flight/FlightDialog.vue";
import FlightTable from "@/components/Flight/FlightTable.vue";

export default {
    name: "FlightInfo",
    components: {
        FlightDialog,
        FlightTable
    },
    data() {
        return {
            queryParam: {
                currentPage: 1,
                pageSize: 10,
                totalRecords: 0,
            },
            edit: {
                departure: "",
                destination: "",
            },
            flight: [],
            dialogFormVisible: false,
            state: "",
            loading: false,
            country: [],
            casProps: {
                value: "value",
                label: "label",
                leaf: true,
                lazy: true,
                lazyLoad: (node, resolve) => {
                    if (node.data.label) this.getChildProject(node.data, resolve);
                },
            },
        };
    }
}