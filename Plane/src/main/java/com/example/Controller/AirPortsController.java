package com.example.Controller;

import com.example.Service.AirPortsService;
import com.example.vo.Air;
import com.example.vo.City;
import com.example.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class AirPortsController {

    final AirPortsService airPortsService;

    //查询国家
    @GetMapping("/selectCountry")
    public ResponseResult selectCountry() {

        return new ResponseResult<>(200, airPortsService.selectCountry());
    }

    //查询城市
    @GetMapping("/selectCity/{countryName}")
    public ResponseResult selectCity(@PathVariable String countryName) {
        City city = new City();
        city.setCountryName(countryName);
        return new ResponseResult<>(200, airPortsService.selectCity(city));
    }

    //查询机场
    @GetMapping("/selectAir/{cityName}")
    public ResponseResult selectAir(@PathVariable String cityName) {
        Air air = new Air();
        air.setCityName(cityName);
        return new ResponseResult<>(200, airPortsService.selectAir(air));
    }
}
