package com.fangxiong.controller;

import com.fangxiong.pojo.JobOption;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.EmpService;
import com.fangxiong.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private EmpService empService;
    @Autowired
    private StudentService studentService;
    //查询各个职位的员工数量
    @GetMapping("/empJobData")
    public Result getEmpJobData()
    {
        log.info("查询各个职位的员工数量");
        JobOption jobOption = empService.getEmpJobData();
        return Result.success(jobOption);
    }
    //查询各个性别的员工数量
    @GetMapping("/empGenderData")
    public Result getEmpGenderData()
    {
        log.info("查询各个性别的员工数量");
        return Result.success(empService.getEmpGenderData());
    }
    //学员信息统计
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData()
    {
        log.info("查询各个学历的学员数量");
        return Result.success(studentService.getStudentDegreeData());
    }
    //班级人数统计
    @GetMapping("/studentCountData")
    public Result getStudentCountData()
    {
        log.info("查询各个班级的学员数量");
        return Result.success(studentService.getStudentCountData());
    }
}
