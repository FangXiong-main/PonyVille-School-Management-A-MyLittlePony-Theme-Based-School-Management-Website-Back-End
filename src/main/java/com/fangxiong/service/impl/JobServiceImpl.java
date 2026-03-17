package com.fangxiong.service.impl;

import com.fangxiong.mapper.JobMapper;
import com.fangxiong.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;
    //获取所有职位
    @Override
    public List<Map<String, Object>> getList() {
        return jobMapper.getList();
    }

    @Override
    public Map<String,Object> getById(Integer id) {
        return jobMapper.getById(id);
    }

    @Override
    public void updateById(Map<String, Object> job) {
        Integer jobId = (Integer) job.get("id");
        String jobName = (String) job.get("name");
        jobMapper.updateById(jobId,jobName);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("根据id删除工作:{}",id);
        jobMapper.deleteById(id);
    }

    @Override
    public void add(Map<String, Object> job) {
        String jobName = (String) job.get("name");
        jobMapper.add(jobName);
    }

}
