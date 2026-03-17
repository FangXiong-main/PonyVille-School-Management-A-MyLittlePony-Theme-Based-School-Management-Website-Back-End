package com.fangxiong.mapper;

import com.fangxiong.pojo.LogResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select *,emp.name operateEmpName from operate_log left join emp on operate_log.operate_emp_id=emp.id")
    List<LogResult> pageInfo();
}
