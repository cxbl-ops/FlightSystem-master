package com.example.Service.impl;

import com.example.Mapper.FlightMapper;
import com.example.Service.FlightService;
import com.example.vo.Flight;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class FlightServiceimpl implements FlightService {
@Resource
private FlightMapper flightMapper;
    @Override
    public List<Flight> selectFlightList() {
        return flightMapper.selectFlightList();
    }

    @Override

    public int editFlight(Flight flight) {

        return flightMapper.editFlight(flight);
    }


    @Override
    public int deleteFlightById(Flight flight) {
        return flightMapper.deleteFlightById(flight);
    }

    @Override
    public int insertFlight(Flight flight) {

        return flightMapper.insertFlight(flight);
    }

    @Override
    public Flight selectFlightById(int id) {
        return flightMapper.selectFlightById(id);
    }

    @Override
    public List<Flight> search(Flight flight) {
        return flightMapper.search(flight);
    }


}
