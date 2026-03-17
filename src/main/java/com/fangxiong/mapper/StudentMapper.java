package com.fangxiong.mapper;

import com.fangxiong.pojo.Student;
import com.fangxiong.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> pageInfo(StudentQueryParam studentQueryParam);

    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time) values(#{name},#{no}," +
            "#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void save(Student student);
    @Select("select id,name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time,violation_count,violation_score from student where id=#{id}")
    Student getById(Integer id);
    @Update("update student set name=#{name},no=#{no},gender=#{gender},phone=#{phone},id_card=#{idCard},is_college=#{isCollege},address=#{address},degree=#{degree},graduation_date=#{graduationDate},clazz_id=#{clazzId},update_time=#{updateTime} where id=#{id}")
    void update(Student student);

    void delete(List<Integer> ids);
    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} where id=#{id}")
    void violation(Integer id, Integer score);
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();
    @MapKey("clazzList")
    List<Map<String, Object>> getStudentCountData();
}
