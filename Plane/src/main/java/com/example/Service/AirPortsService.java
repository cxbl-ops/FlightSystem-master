package com.example.Service;

import com.example.vo.Air;
import com.example.vo.City;
import com.example.vo.Country;

import java.util.List;

public interface AirPortsService {
    List<Country> selectCountry( );
    List<City> selectCity (City city);
    List<Air> selectAir(Air air);
}
