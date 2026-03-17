package com.fangxiong.service.impl;

import com.fangxiong.mapper.ClazzMapper;
import com.fangxiong.pojo.Clazz;
import com.fangxiong.pojo.ClazzQueryParam;
import com.fangxiong.pojo.PageResult;
import com.fangxiong.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service //服务层
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    //分页查询，使用PageHelper插件实现分页查询
    @Override
    public PageResult<Clazz> pageInfo(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> pageList = clazzMapper.pageInfo(clazzQueryParam);
        if(!CollectionUtils.isEmpty(pageList))
        {
            pageList.forEach(clazz -> {
                if(LocalDate.now().isAfter(clazz.getBeginDate())&&LocalDate.now().isBefore(clazz.getEndDate()))
                {
                    clazz.setStatus("在读中");
                }
                else if(LocalDate.now().isAfter(clazz.getEndDate()))
                {
                    clazz.setStatus("已结课");
                }
                else
                {
                    clazz.setStatus("未开班");
                }
            });
        }
        Page<Clazz> p = (Page<Clazz>) pageList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        //为创建新的班级添加创建时间和修改时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.save(clazz);
    }
    //查询回显
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }
    //修改班级信息
    @Override
    public void update(Clazz clazz) {
        //对最后修改时间进行更新
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
    //删除班级信息
    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }
    //查询班级列表
    @Override
    public List<Clazz> getClazzList() {
        return clazzMapper.getClazzList();
    }

    @Override
    public List<Map<String, Object>> getSubjectList() {
        return clazzMapper.getSubjectList();
    }


}
