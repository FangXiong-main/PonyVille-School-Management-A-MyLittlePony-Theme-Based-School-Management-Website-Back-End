package com.fangxiong.service;

import com.fangxiong.pojo.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    JobOption getEmpJobData();

    List<Map<String,Object>> getEmpGenderData();

    List<Emp> getMasterList();

    LoginResult selectByUsernameAndPassword(Emp emp);
}
