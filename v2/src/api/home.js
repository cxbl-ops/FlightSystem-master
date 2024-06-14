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

export function deleteFlightById(id) {
  return request({
    method: "post",
    url: "/dev-api/deleteFlightById",
    data: id,
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
export function editFlight(flightId) {
  return request({
    method: "post",
    url: "/dev-api/editFlight",
    data: flightId,
  });
}
export function addFlight(data) {
  return request({
    method: "post",
    url: "/dev-api/insertFlight",
    data,
  });
}
export function search(flightId) {
    return request({
      method: "get",
      url: "/dev-api/search/"+flightId,
    });

}
