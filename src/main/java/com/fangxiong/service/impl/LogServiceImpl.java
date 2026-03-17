package com.fangxiong.service.impl;

import com.fangxiong.mapper.LogMapper;
import com.fangxiong.pojo.LogResult;
import com.fangxiong.pojo.PageResult;
import com.fangxiong.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public PageResult<LogResult> pageInfo(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<LogResult> logResults = logMapper.pageInfo();
        Page<LogResult> p=(Page<LogResult>) logResults;
        return new PageResult<LogResult>(p.getTotal(),p.getResult());
    }
}
