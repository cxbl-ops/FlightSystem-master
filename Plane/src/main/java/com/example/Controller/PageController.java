package com.example.Controller;

import com.example.Mapper.PageMapper;
import com.example.Service.PageService;
import com.example.vo.ResponseResult;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@RestController
public class PageController {

    final PageService pageService;

    final PageMapper pageMapper;
    @GetMapping("/queryPageByFlight")
    public  ResponseResult queryPageByFlight(@RequestParam(defaultValue = "1") Integer currPage, @RequestParam(defaultValue = "10") Integer pageSize){

        currPage=(currPage-1)*pageSize;

        int total = pageMapper.queryPageCount();
        Map<String, Object> map = new HashMap<>();
        map.put("list", pageService.queryPageByFlight(currPage, pageSize));
        map.put("total", total);
        return new ResponseResult<>(200,map);
    }
}
