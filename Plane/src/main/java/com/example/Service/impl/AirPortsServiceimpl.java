package com.example.Service.impl;

import com.example.Mapper.AirPortsMapper;
import com.example.vo.Air;
import com.example.vo.City;
import com.example.vo.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Service.AirPortsService;

import java.util.List;

@Service
public class AirPortsServiceimpl implements AirPortsService {

    @Autowired
    private AirPortsMapper airPortsMapper;

    @Override
    public List<Country> selectCountry( ) {
        return airPortsMapper.selectCountry();
    }

    @Override
    public List<City> selectCity(City city) {
        return airPortsMapper.selectCity(city);
    }

    @Override
    public List<Air> selectAir(Air air) {
        return airPortsMapper.selectAir(air);
    }
}
