package com.fangxiong.service.impl;

import com.fangxiong.mapper.SubjectMapper;
import com.fangxiong.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Map<String, Object>> getSubjects() {
        return subjectMapper.getSubjects();
    }

    @Override
    public void updateSub(Map<String, Object> sub) {
        String sub_name = (String) sub.get("name");
        Integer id = (Integer) sub.get("id");
        subjectMapper.updateSub(sub_name,id);
    }

    @Override
    public void delete(Integer id) {
        subjectMapper.delete(id);
    }

    @Override
    public void add(String name) {
        subjectMapper.add(name);
    }

    @Override
    public Map<String, Object> getById(Integer id) {
        return subjectMapper.getById(id);
    }
}
