package com.fangxiong.mapper;

import com.fangxiong.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void saveBatch(List<EmpExpr> empExprList);

    void deleteByEmpId(List<Integer> ids);
}
