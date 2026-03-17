package com.fangxiong.controller;

import com.fangxiong.pojo.LogResult;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result pageInfo(Integer page,Integer pageSize)
    {
        log.info("分页查询，参数：{},{}",page,pageSize);
        return Result.success(logService.pageInfo(page,pageSize));
    }
}
