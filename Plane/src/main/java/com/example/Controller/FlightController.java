package com.example.Controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONArray;
import com.example.Service.FlightService;
import com.example.WeBaseUtil.api;
import com.example.vo.Flight;
import com.example.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FlightController {
    @Resource
    private  FlightService flightService;
    //todo 查询航班信息
    @GetMapping("/selectFlight")
    public ResponseResult selectFlight() {
        return new ResponseResult<>(200, flightService.selectFlightList());
    }

    @PostMapping("/editFlight")
    public ResponseResult editFlight(@RequestBody Flight flight) {
       int res = flightService.editFlight(flight);
        System.out.println(res);
        System.out.println(flight);
            return new ResponseResult<>(200, flightService.editFlight(flight));
    }

    @PostMapping("/deleteFlightById")
    public ResponseResult deleteFlightById(@RequestBody Flight flight)  {

        return new ResponseResult<>(200, flightService.deleteFlightById(flight));

    }

    @PostMapping("/insertFlight")
    public ResponseResult insertFlight(@RequestBody Flight flight) {
        System.out.println(flight);
        JSONArray _flightInfo = new JSONArray();
        _flightInfo.put(flight.getFlightId());
        _flightInfo.put(flight.getPlanTime());
        _flightInfo.put(flight.getEstimatedTime());
        _flightInfo.put(flight.getActualTime());
        _flightInfo.put(flight.getActualArrived());
        _flightInfo.put(flight.getDeparture());
        _flightInfo.put(flight.getDestination());
        _flightInfo.put(flight.getDelay());
//
        Dict getResult = api.LocalSign("Flight", "0xf6971570cf32251b0653b25a31025f0eeb7384ec", "addFlight", _flightInfo,
        "[{\"constant\":false,\"inputs\":[{\"name\":\"_flightId\",\"type\":\"string\"},{\"name\":\"_planTime\",\"type\":\"string\"},{\"name\":\"_estimatedTime\",\"type\":\"string\"},{\"name\":\"_actualTime\",\"type\":\"string\"},{\"name\":\"_actualArrived\",\"type\":\"string\"},{\"name\":\"_departure\",\"type\":\"string\"},{\"name\":\"_destination\",\"type\":\"string\"},{\"name\":\"_delay\",\"type\":\"bool\"}],\"name\":\"addFlight\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_flightId\",\"type\":\"string\"}],\"name\":\"getFlightInfoByFlightId\",\"outputs\":[{\"components\":[{\"name\":\"id\",\"type\":\"int256\"},{\"name\":\"flightId\",\"type\":\"string\"},{\"name\":\"planTime\",\"type\":\"string\"},{\"name\":\"estimatedTime\",\"type\":\"string\"},{\"name\":\"actualTime\",\"type\":\"string\"},{\"name\":\"actualArrived\",\"type\":\"string\"},{\"name\":\"departure\",\"type\":\"string\"},{\"name\":\"destination\",\"type\":\"string\"},{\"name\":\"delay\",\"type\":\"bool\"}],\"name\":\"\",\"type\":\"tuple\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"name\":\"flights\",\"outputs\":[{\"name\":\"id\",\"type\":\"int256\"},{\"name\":\"flightId\",\"type\":\"string\"},{\"name\":\"planTime\",\"type\":\"string\"},{\"name\":\"estimatedTime\",\"type\":\"string\"},{\"name\":\"actualTime\",\"type\":\"string\"},{\"name\":\"actualArrived\",\"type\":\"string\"},{\"name\":\"departure\",\"type\":\"string\"},{\"name\":\"destination\",\"type\":\"string\"},{\"name\":\"delay\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"id\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]");
        System.out.println("getResult:"+ getResult);
        return new ResponseResult<>(200, flightService.insertFlight(flight));
//        return  new ResponseResult<>(200,getResult);
    }
    @GetMapping("/selectFlightById")
    public  ResponseResult getFlight(int id){
        System.out.println(id);
        return new ResponseResult(200,flightService.selectFlightById(id));
    }
    @GetMapping("/search/{flightId}")
    public  ResponseResult search(@PathVariable String flightId){
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        return new ResponseResult(200,flightService.search(flight));
    }
}
