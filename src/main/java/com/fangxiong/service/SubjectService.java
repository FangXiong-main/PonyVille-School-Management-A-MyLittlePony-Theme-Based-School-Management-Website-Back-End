package com.fangxiong.service;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    List<Map<String,Object>> getSubjects();

    void updateSub(Map<String, Object> sub);

    void delete(Integer id);

    void add(String name);

    Map<String,Object> getById(Integer id);
}
