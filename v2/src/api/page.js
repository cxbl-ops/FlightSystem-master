import request from "@/util/request.js"
export function queryPageByFlight(data) {
    const currPage = data.currentPage
    const pageSize = data.pageSize
    return request({
        method: "get",
        url: "/dev-api/queryPageByFlight?currPage=" + currPage + "&pageSize=" + pageSize,
    });
}