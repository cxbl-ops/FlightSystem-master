package com.example.Service;

import com.example.vo.Flight;

import java.util.List;

public interface PageService {
    List<Flight> queryPageByFlight(int currPage,int pageSize);
}
