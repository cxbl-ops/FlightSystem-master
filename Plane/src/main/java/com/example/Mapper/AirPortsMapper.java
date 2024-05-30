package com.example.Mapper;

import com.example.vo.Air;
import com.example.vo.City;
import com.example.vo.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirPortsMapper {
    List<Country> selectCountry();
    List<City> selectCity (City city);
    List<Air> selectAir(Air air);
}
