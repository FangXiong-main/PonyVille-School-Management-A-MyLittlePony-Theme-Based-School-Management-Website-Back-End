package com.fangxiong.service.impl;

import com.fangxiong.mapper.DeptMapper;
import com.fangxiong.pojo.Dept;
import com.fangxiong.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }

    @Override
    public Boolean delete(Integer id) {
        //查询该部门下是否有员工,如果没有，则删除该部门，如果有，则不能删除该部门
        Integer empCount = deptMapper.getEmpCount(id);
        if(empCount==0) {
            deptMapper.delete(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        //1.补全修改时间
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
