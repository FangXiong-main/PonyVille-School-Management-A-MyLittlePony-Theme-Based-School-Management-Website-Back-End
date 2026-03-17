package com.fangxiong.service;

import com.fangxiong.pojo.ClazzOption;
import com.fangxiong.pojo.PageResult;
import com.fangxiong.pojo.Student;
import com.fangxiong.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageResult<Student> pageInfo(StudentQueryParam studentQueryParam);

    void save(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void violation(Integer id, Integer score);

    List<Map<String,Object>> getStudentDegreeData();

    ClazzOption getStudentCountData();
}
