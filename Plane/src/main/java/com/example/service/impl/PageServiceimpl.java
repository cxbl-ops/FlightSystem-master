package com.example.service.impl;

import com.example.mapper.PageMapper;
import com.example.service.PageService;
import com.example.vo.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PageServiceimpl implements PageService {

    final PageMapper pageMapper;
    @Override
    public List<Flight> queryPageByFlight(int currPage, int pageSize){
        return pageMapper.queryPageByFlight(currPage,pageSize);
    }
}
