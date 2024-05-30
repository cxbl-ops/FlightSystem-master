package com.example.Mapper;

import com.example.vo.Flight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageMapper {

    int queryPageCount();
    List<Flight> queryPageByFlight(int currPage, int pageSize);
}
