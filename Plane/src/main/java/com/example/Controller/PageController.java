package com.example.Controller;

import com.example.Mapper.PageMapper;
import com.example.Service.PageService;

import com.example.vo.ResponseResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PageController {
    @Resource
    private PageService pageService;
    @Autowired
    private PageMapper pageMapper;
    @GetMapping("/queryPageByFlight")
    public  ResponseResult queryPageByFlight(@RequestParam(defaultValue = "1") Integer currPage,@RequestParam(defaultValue = "10") Integer pageSize){

        currPage=(currPage-1)*pageSize;

        int total = pageMapper.queryPageCount();
        Map<String, Object> map = new HashMap<>();
        map.put("list", pageService.queryPageByFlight(currPage, pageSize));
        map.put("total", total);
        return new ResponseResult(200,map);
    }
}
