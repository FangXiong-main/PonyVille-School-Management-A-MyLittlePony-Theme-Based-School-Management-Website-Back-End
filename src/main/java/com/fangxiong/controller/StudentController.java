package com.fangxiong.controller;

import com.fangxiong.anno.Log;
import com.fangxiong.pojo.PageResult;
import com.fangxiong.pojo.Result;
import com.fangxiong.pojo.Student;
import com.fangxiong.pojo.StudentQueryParam;
import com.fangxiong.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    //分页查询所有学员信息
    @GetMapping
    public Result pageInfo(StudentQueryParam studentQueryParam)
    {
        log.info("分页查询，参数：{}",studentQueryParam);
        return Result.success(studentService.pageInfo(studentQueryParam));
    }
    //新增学员信息
    @Log
    @PostMapping
    public Result save(@RequestBody Student student)
    {
        log.info("新增学员信息，参数：{}",student);
        studentService.save(student);
        return Result.success();
    }
    //查询回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) //@PathVariable将路径参数绑定到变量上
    {
        log.info("根据id查询学员信息：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
    //修改学员信息
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) //@RequestBody将请求体中json格式的数据转换为java对象
    {
        log.info("修改学员信息：{}",student);
        studentService.update(student);
        return Result.success();
    }
    //批量删除学生信息
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids)
    {
        log.info("批量删除学生信息：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }
    //违纪处理
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score)
    {
        log.info("违纪处理，id:{},score:{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
