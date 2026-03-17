package com.fangxiong.controller;

import com.fangxiong.anno.Log;
import com.fangxiong.mapper.EmpMapper;
import com.fangxiong.pojo.Emp;
import com.fangxiong.pojo.EmpQueryParam;
import com.fangxiong.pojo.PageResult;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private EmpMapper empMapper;

    //1.分页查询（原始方法）
//    @GetMapping
//    //设置page和pageSize为默认值1和10
//    //使用@RequestParam注解，如果参数没有传递，则使用默认值1和10
//    public Result page(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10") Integer pageSize)
//    {
//        log.info("分页查询，当前页码：{}，每页条数：{}",page,pageSize);
//        PageResult<Emp> pageresult = empService.page(page, pageSize);
//        return Result.success(pageresult);
//    }
    //1.分页查询，使用pageHelper插件
    //在原有基础上添加条件
    //使用LocalDate接收前端传输的数据的时候要使用@DateTimeFormat注解，在其属性pattern中声明时间格式
    @GetMapping
    public Result page(EmpQueryParam empQueryparam) //优化：将多个条件封装到对象中，避免参数过多
    {
        log.info("分页查询条件:{}",empQueryparam);
        PageResult<Emp> pageresult = empService.page(empQueryparam);
        return Result.success(pageresult);
    }
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) //@RequestBody将请求体中json格式的数据转换为java对象
    {
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }
    //方式一：使用数组接收多个id
//    @DeleteMapping
//    public Result delete(Integer[] ids)
//    {
//        log.info("根据id删除员工：{}",ids);
//        return Result.success();
//    }
    //删除员工
    //方式二：使用List接收多个id
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids)
    {
        log.info("根据id删除员工：{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    //查询回显(使用路径参数)
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) //@PathVariable将路径参数绑定到变量上
    {
        log.info("根据id查询员工信息：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    //修改员工信息
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) //@RequestBody将请求体中json格式的数据转换为java对象
    {
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
    //用于添加班级列表的班主任显示
    @GetMapping("/list")
    public Result getMasterList()
    {
        log.info("获取员工列表用于添加班级选择班主任功能");
        List<Emp> empList = empMapper.getMasterlist();
        return Result.success(empList);
    }
}
