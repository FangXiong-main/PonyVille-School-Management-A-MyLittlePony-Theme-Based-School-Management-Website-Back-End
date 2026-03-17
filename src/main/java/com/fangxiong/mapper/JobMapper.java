package com.fangxiong.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobMapper {
    @Select("select id,job_name name from job")
    List<Map<String, Object>> getList();
    @Select("select id,job_name name from job where job.id=#{id}")
    Map<String,Object> getById(Integer id);
    @Update("update job set job_name = #{name} where id = #{id}")
    void updateById(Integer id, String name);
    @Delete("delete from job where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into job values(null,#{jobName})")
    void add(String jobName);
}
