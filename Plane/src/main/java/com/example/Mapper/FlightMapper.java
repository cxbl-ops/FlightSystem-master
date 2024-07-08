package com.example.Mapper;

import com.example.vo.Flight;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlightMapper {

    @Select("select * from tb_flight")
    List<Flight> selectFlightList();

    @Update(" UPDATE tb_flight SET flightId = #{flightId}, planTime = #{planTime},estimatedTime = #{estimatedTime},actualTime = #{actualTime},actualArrived = #{actualArrived},departure = #{departure},destination = #{destination}, delay = #{delay}, isCompleted = #{isCompleted} WHERE id = #{id}")
    int editFlight(Flight flight);

    @Delete("DELETE FROM tb_flight WHERE id=#{id}")
    int deleteFlightById(Flight flight);

    @Insert("insert into tb_flight(flightId,planTime,estimatedTime,actualTime,actualArrived,departure,destination,delay,isCompleted) values (#{flightId},#{planTime},#{estimatedTime},#{actualTime},#{actualArrived},#{departure},#{destination},#{delay},#{isCompleted})")
    int insertFlight(Flight flight);

    @Select("select * from tb_flight where flightId =  #{flightId}")
    Flight selectFlightById(int id);

    @Select("select * from tb_flight where flightId like #{flightId}")
    List<Flight> search(Flight flight);


}
