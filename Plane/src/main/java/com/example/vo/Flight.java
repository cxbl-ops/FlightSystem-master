package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.lang.reflect.Array;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private Integer id;
    //航班号
    private String flightId;

    private String planTime;

    private  String estimatedTime;
    //实际起飞时间
    private String actualTime;
    //实际到达时间
    private String actualArrived;
    //出发地
    private String departure;
    //目的地
    private  String destination;
    //是否延误
    private String delay;
    private  String isCompleted;

    private String total;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightId='" + flightId + '\'' +
                ", planTime='" + planTime + '\'' +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", actualTime='" + actualTime + '\'' +
                ", actualArrived='" + actualArrived + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", delay='" + delay + '\'' +
                ", isCompleted='" + isCompleted + '\'' +
                '}';
    }
}
