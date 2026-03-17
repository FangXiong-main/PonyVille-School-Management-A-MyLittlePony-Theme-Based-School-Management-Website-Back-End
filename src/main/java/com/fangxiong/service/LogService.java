package com.fangxiong.service;

import com.fangxiong.pojo.LogResult;
import com.fangxiong.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LogService {

    PageResult<LogResult> pageInfo(Integer page, Integer pageSize);
}
