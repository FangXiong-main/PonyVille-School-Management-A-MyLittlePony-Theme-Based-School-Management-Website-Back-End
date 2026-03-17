package com.fangxiong.service;

import java.util.List;
import java.util.Map;

public interface JobService {

    List<Map<String,Object>> getList();

    Map<String,Object> getById(Integer id);

    void updateById(Map<String, Object> job);

    void deleteById(Integer id);

    void add(Map<String, Object> job);
}
