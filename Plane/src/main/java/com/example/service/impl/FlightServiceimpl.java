package com.example.service.impl;

import com.example.mapper.FlightMapper;
import com.example.service.FlightService;
import com.example.vo.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class FlightServiceimpl implements FlightService {

final FlightMapper flightMapper;
    @Override
    public List<Flight> selectFlightList() {
        return flightMapper.selectFlightList();
    }

    @Override

    public int editFlight(Flight flight) {

        return flightMapper.editFlight(flight);
    }




    @Override
    public int deleteFlightById(int id) {
        return flightMapper.deleteFlightById(id);
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
