import request from "@/util/request";

export function selectCountry() {
  return request({
    method: "get",
    url: "/dev-api/selectCountry",
  });
}
export function selectCity(countryName) {
    return request({
      method: "get",
      url: "/dev-api/selectCity/"+countryName,
    });
  }
  export function selectAir(id) {
    return request({
      method: "get",
      url: "/dev-api/selectAir/"+id,
    });
  }

