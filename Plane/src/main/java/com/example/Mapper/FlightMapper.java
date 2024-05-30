package com.example.Mapper;

import com.example.vo.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;

import java.util.List;

@Mapper
public interface FlightMapper {
    List<Flight> selectFlightList();
    int editFlight(Flight flight);
    int deleteFlightById(Flight flight);
    int insertFlight(Flight flight);

    Flight selectFlightById(int id);

    List<Flight> search(Flight flight);


}
