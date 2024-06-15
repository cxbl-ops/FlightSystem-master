import request from "@/util/request";

export function login(username, account, passwd) {
    const data = {
        username,
        account,
        passwd,
    };
    return request({
        method: "post",
        url: "/dev-api/login",
        data: data,
    });
}

export function register(data) {
    return request({
        method: "post",
        url: "/dev-api/register",
        data: data,
    });
}
export function sendEmail(data) {
    return request({
        method: "get",
        url: "/dev-api/sendEmail/" + data,
    });
}
export function registerE(data) {
    return request({
        method: "post",
        url: "/dev-api/register",
        data,
    });
}