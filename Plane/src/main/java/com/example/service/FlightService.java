package com.example.service;

import com.example.vo.Flight;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightService {
    List<Flight> selectFlightList();
   int editFlight(Flight flight);
    int deleteFlightById(@Param("id") int id);
   int insertFlight(Flight flight);

    Flight selectFlightById(int id);
    List<Flight> search(Flight flight);

}
