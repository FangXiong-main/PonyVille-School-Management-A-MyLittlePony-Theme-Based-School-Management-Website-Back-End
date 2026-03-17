package com.fangxiong.controller;

import com.fangxiong.Utils.CurrentHolder;
import com.fangxiong.anno.Log;
import com.fangxiong.pojo.Clazz;
import com.fangxiong.pojo.ClazzQueryParam;
import com.fangxiong.pojo.PageResult;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    //分页查询所有数据
    @GetMapping //不使用@RequestBody注解，使用@RequestBody注解是为了接收前端传递的json数据
    public Result pageInfo(ClazzQueryParam clazzQueryParam)
    {
        log.info("分页查询，参数：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.pageInfo(clazzQueryParam);
        return Result.success(pageResult);
    }
    //添加班级
    @Log
    @PostMapping
    public Result save(@RequestBody Clazz clazz)
    {
        log.info("添加班级，参数：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }
    //查询回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) //@PathVariable将路径参数绑定到变量上
    {
        log.info("根据id查询班级信息：{}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    //修改班级信息
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) //@RequestBody将请求体中json格式的数据转换为java对象
    {
        log.info("修改班级信息：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    //删除班级信息
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) //@PathVariable将路径参数绑定到变量上
    {
        log.info("删除班级信息依据id：{}",id);
        clazzService.delete(id);
        return Result.success();
    }
    //查询所有班级用于新增学员下拉框
    @GetMapping("/list")
    public Result getClazzList()
    {
        log.info("查询所有班级用于新增学员下拉框");
        return Result.success(clazzService.getClazzList());
    }

    //查询学科列表
    @GetMapping("/subjects")
    public Result getSubjectList()
    {
        log.info("查询学科列表");
        return Result.success(clazzService.getSubjectList());
    }
}
