package com.fangxiong.service;

import com.fangxiong.anno.Log;
import com.fangxiong.pojo.Clazz;
import com.fangxiong.pojo.ClazzQueryParam;
import com.fangxiong.pojo.PageResult;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ClazzService {
    PageResult<Clazz> pageInfo(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);
    
    Clazz getById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> getClazzList();

    @MapKey("subject")
    List<Map<String,Object>> getSubjectList();
}
