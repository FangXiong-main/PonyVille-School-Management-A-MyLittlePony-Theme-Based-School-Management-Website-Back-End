package com.fangxiong.controller;

import com.fangxiong.anno.Log;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    //查询学科列表
    @GetMapping
    public Result getSubjects() {
        log.info("查询开放学科列表");
        return Result.success(subjectService.getSubjects());
    }

    @Log
    //修改学科列表
    @PutMapping
    public Result update(@RequestBody Map<String,Object> Sub) {
        log.info("修改学科:{},{}", Sub.get("id"),  Sub.get("name"));
        subjectService.updateSub(Sub);
        return Result.success();
    }

    @Log
    //删除学科
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除学科:{}", id);
        subjectService.delete(id);
        return Result.success();
    }
    @Log
    //新增学科
    @PostMapping
    public Result add(@RequestBody Map<String,Object> sub){
        String sub_name=sub.get("name").toString();
        log.info("新增学科:{}",sub_name);
        subjectService.add(sub_name);
        return Result.success();
    }
    //查询回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询学科信息：{}",id);
        return Result.success(subjectService.getById(id));
    }
}
