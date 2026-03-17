package com.fangxiong.controller;

import com.fangxiong.anno.Log;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/jobs")
@RestController
public class JobController {
    @Autowired
    private JobService jobService;
    //查询工作列表(少量无需分页查询)
    @GetMapping
    public Result List()
    {
        log.info("查询工作列表");
        return Result.success(jobService.getList());
    }
    //查询回显（使用路径参数）
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询工作");
        return Result.success(jobService.getById(id));
    }
    @Log
    //修改工作
    @PutMapping
    public Result update(@RequestBody Map<String,Object> job){
        log.info("根据id修改工作：{}",job.get("id"));
        jobService.updateById(job);
        return Result.success();
    }

    @Log
    //删除工作
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除工作");
        jobService.deleteById(id);
        return Result.success();
    }

    @Log
    //添加工作
    @PostMapping
    public Result add(@RequestBody Map<String,Object> job){
        log.info("添加工作:{}",job.get("name"));
        jobService.add(job);
        return Result.success();
    }
}
