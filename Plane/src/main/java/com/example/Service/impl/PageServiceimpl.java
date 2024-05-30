package com.example.Service.impl;

import com.example.Mapper.PageMapper;
import com.example.Service.PageService;
import com.example.vo.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PageServiceimpl implements PageService {
    @Autowired
    private PageMapper pageMapper;
    @Override
    public List<Flight> queryPageByFlight(int currPage, int pageSize){
//        Map<String,Object> data = new HashMap<>();
//        data.put("currIndex",(currPage-1)*pageSize);
//        data.put("pageSize",pageSize);
        return pageMapper.queryPageByFlight(currPage,pageSize);
    }
}
