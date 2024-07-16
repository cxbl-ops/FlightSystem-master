package com.example.mapper;

import com.example.vo.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PageMapper {
    @Select("SELECT COUNT(*) total FROM tb_flight")
    int queryPageCount();

    @Select("SELECT * FROM tb_flight LIMIT #{currPage}, #{pageSize}")
    List<Flight> queryPageByFlight(int currPage, int pageSize);
}
