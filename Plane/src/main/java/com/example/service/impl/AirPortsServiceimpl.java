package com.example.service.impl;

import com.example.mapper.AirPortsMapper;
import com.example.service.AirPortsService;
import com.example.vo.Air;
import com.example.vo.City;
import com.example.vo.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class AirPortsServiceimpl implements AirPortsService {


    final AirPortsMapper airPortsMapper;

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
