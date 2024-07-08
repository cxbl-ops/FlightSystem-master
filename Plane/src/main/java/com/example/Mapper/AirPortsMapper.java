package com.example.Mapper;

import com.example.vo.Air;
import com.example.vo.City;
import com.example.vo.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AirPortsMapper {

    @Select("select id ,countryName  from tb_country")
    List<Country> selectCountry();

    @Select("SELECT DISTINCT tb_city.id, tb_city.cityName  FROM tb_city  INNER JOIN tb_country ON tb_city.countryId = tb_country.id WHERE tb_country.countryName = #{countryName}")
    List<City> selectCity (City city);

    @Select("select tb_air.id, tb_air.airName FROM tb_air INNER JOIN tb_city on tb_air.id = tb_city.id WHERE tb_city.cityName = #{cityName}")
    List<Air> selectAir(Air air);
}