package com.fangxiong.mapper;

import com.fangxiong.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //起别名方式一：通过@Results注解
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    //起别名方式二，直接在SQL中起别名
    //select id,name,create_time createTime,update_time updateTime from dept ORDER BY update_time desc
    @Select("select id,name,create_time,update_time from dept ORDER BY update_time desc")
    List<Dept> findAll();
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);
    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept findById(Integer id);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
    @Select("select count(*) from emp where emp.dept_id=#{id}")
    Integer getEmpCount(Integer id);
}
