import request from "@/util/request";


export function ApideleteFlightById(id) {
  return request({
    method: "post",
    url: "/dev-api/deleteFlightById",
    data: {id}
  });
}

export function ApieditFlight(flightId) {
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
