package com.fangxiong.service.impl;

import com.fangxiong.mapper.StudentMapper;
import com.fangxiong.pojo.*;
import com.fangxiong.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    //分页查询学生信息
    @Override
    public PageResult<Student> pageInfo(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        Page<Student> pageList = (Page<Student>) studentMapper.pageInfo(studentQueryParam);
        return new PageResult<>(pageList.getTotal(),pageList.getResult());
    }
    //添加学生信息
    @Override
    public void save(Student student) {
        //写入新的创建时间和修改时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }
    //查询回显
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }
    //修改学生信息
    @Override
    public void update(Student student) {
        //更新最后修改时间
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }
    //批量删除学生信息
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id,score);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
    //统计每个班级的人数
    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String,Object>> result=studentMapper.getStudentCountData();
        List<Object> clazzList=result.stream().map(item->item.get("clazzList")).toList();
        List<Object> dataList=result.stream().map(item->item.get("dataList")).toList();
        return new ClazzOption(clazzList,dataList);
    }


}
