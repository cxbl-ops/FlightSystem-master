package com.example.Controller;

import com.example.Service.AirPortsService;
import com.example.vo.City;
import com.example.vo.Country;
import com.example.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import  com.example.vo.Air;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
public class AirPortsController {
    @Resource
    private AirPortsService airPortsService;
    @GetMapping("/selectCountry")
    public ResponseResult selectCountry(){

        return new ResponseResult<>(200, airPortsService.selectCountry());
    }

    @GetMapping("/selectCity/{countryName}")
    public ResponseResult selectCity(@PathVariable String countryName) {
        City city = new City();
        city.setCountryName(countryName);
        return new ResponseResult<>(200, airPortsService.selectCity(city));
    }

    @GetMapping("/selectAir/{cityName}")
    public ResponseResult selectAir(@PathVariable String cityName ) {
        Air air = new Air();
        air.setCityName(cityName);
        return new ResponseResult<>(200, airPortsService.selectAir(air));
    }
}
