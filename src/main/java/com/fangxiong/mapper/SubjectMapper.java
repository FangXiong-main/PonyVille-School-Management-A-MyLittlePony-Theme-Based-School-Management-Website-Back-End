package com.fangxiong.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubjectMapper {
    @Select("select id,sub_name name from subject")
    List<Map<String, Object>> getSubjects();
    @Update("update subject set sub_name = #{subName} where id = #{id}")
    void updateSub(String subName, Integer id);
    @Delete("delete from subject where id = #{id}")
    void delete(Integer id);
    @Insert("insert into subject values(null,#{name})")
    void add(String name);
    @Select("select id,sub_name name from subject where id = #{id}")
    Map<String,Object> getById(Integer id);
}
