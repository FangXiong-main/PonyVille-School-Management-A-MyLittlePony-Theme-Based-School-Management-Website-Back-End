package com.fangxiong.service;

import com.fangxiong.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    Boolean delete(Integer id);

    void add(Dept dept);

    Dept findById(Integer id);

    void update(Dept dept);
}
