package com.example.Service;

import com.example.vo.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> selectFlightList();
   int editFlight(Flight flight);
    int deleteFlightById(Flight flight);
   int insertFlight(Flight flight);

    Flight selectFlightById(int id);
    List<Flight> search(Flight flight);

}
