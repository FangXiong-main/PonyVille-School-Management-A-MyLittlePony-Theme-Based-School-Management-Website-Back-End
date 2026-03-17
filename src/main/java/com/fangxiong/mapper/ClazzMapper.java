package com.fangxiong.mapper;

import com.fangxiong.pojo.Clazz;
import com.fangxiong.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {
    List<Clazz> pageInfo(ClazzQueryParam clazzQueryParam);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values(#{name},#{room},#{beginDate}," +
            "#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void save(Clazz clazz);
    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz where id=#{id}")
    Clazz getById(Integer id);
    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void update(Clazz clazz);
    @Delete("delete from clazz where id = #{id}")
    void delete(Integer id);
    @Select("select id,name from clazz")
    List<Clazz> getClazzList();
    @Select("select id,sub_name name from subject")
    List<Map<String, Object>> getSubjectList();
}
