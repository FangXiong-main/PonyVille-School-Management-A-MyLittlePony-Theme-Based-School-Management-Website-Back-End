package com.fangxiong.controller;

import com.fangxiong.anno.Log;
import com.fangxiong.pojo.Dept;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
    //使用lombok中的Slf4j注解
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    //设置请求方式仅仅为GET方式可行
    @GetMapping("/depts")
    public Result findAll() {
        log.info("查询全部部门信息");
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
    }

    //删除部门方式一：使用HttpServletRequest
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String request_id = request.getParameter("id");
//        Integer id= Integer.parseInt(request_id);
//        System.out.println("this is"+id);
//        return Result.success();
//    }
    //方式二：使用@RequestParam
    //默认需要传入参数，不然会报错,设置required = false属性后不传参数也不会报错了
//    "timestamp": "2025-04-02T03:15:34.232+00:00",
//            "status": 400,
//            "error": "Bad Request",
//            "path": "/depts"
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer id) {
//        System.out.println("this is "+id);
//        return Result.success();
//    }
    //方式三：直接使用形参，省略@RequestParam注解
    //如果请求参数名与形参相同，则不需要使用@RequestParam注解可以省略
    @Log
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        log.info("删除部门id为：{}",id);
        if(deptService.delete(id)){
            return Result.success();
        }
        else{
            return Result.error("对不起，当前部门下有员工，不能直接删除！");
        }
    }
    //添加部门,使用PostMapping
    //使用@RequestBody注解接收json格式的数据,可以自动将json数据转换为java对象
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept)
    {
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    //查询回显，根据id查询部门
    //采用路径参数
    //@PathVariable注解用于接收url中的占位符
    //也可以接收多个参数，多个参数之间用逗号隔开，分别加上@PathVariable注解
    @GetMapping("/depts/{id}")
    public Result findById(@PathVariable Integer id)
    {
        log.info("根据id查询部门：{}",id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }
    //修改部门信息
    //使用@RequestBody注解接收json格式的数据,可以自动将json数据转换为java对象
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept)
    {
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}

