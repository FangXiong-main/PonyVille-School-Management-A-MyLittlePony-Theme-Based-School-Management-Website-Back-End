package com.fangxiong.service.impl;

import com.fangxiong.Utils.JWTUtils;
import com.fangxiong.mapper.EmpExprMapper;
import com.fangxiong.mapper.EmpMapper;
import com.fangxiong.pojo.*;
import com.fangxiong.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    //查询回显
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }
    //使用分页查询(使用pageHelper插件)
    //插件使用的注意事项：
    //1.sql语句结尾不能有；
    //2.使用插件的代码必须写在查询语句的前面，不能写在查询语句的后面，而且仅能对紧跟其第一个查询进行分页处理

    //在原有基础上改造为带条件的查询
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //使用PageHelper设置分页查询条件
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList = empMapper.page(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
    //知识点：
    //事务的传播，默认是REQUIRED，表示当前方法需要事务控制，如果当前没有事务，则创建一个事务，如果当前有事务，则加入到当前事务中。
    //在Transactional的属性“propagation”中设置，常用的行为：REQUIRED（默认）、REQUIRES_NEW（常用）、SUPPORTS、NOT_SUPPORTED、MANDATORY、NEVER
    //REQUIRES_NEW表示新开启一个事务，如果当前有事务，则挂起当前事务，开启一个新的事务，新事务结束后，恢复挂起的事务。
    //事务的传播一般用于：1.多个数据库操作，2.多个远程服务调用，3.多个文件操作，4.多个消息队列操作。
    //如果一个事务中有另一个操作，然而另一个操作无论这个事务是否成功都会执行自己的事务，而不会因为主事务的回滚而导致另一个操作的回滚。

    //事务控制常用于多个数据库操作，保证同时成功或失败，单个操作失败，则回滚
    //对于只有单个操作，就没有必要使用事务控制。
    @Transactional(rollbackFor = {Exception.class}) //添加事务注解,确保添加员工和添加员工经历同时成功或失败(由Spring框架提供)
    //默认出现运行时异常才会回滚，RuntimeException是编译时异常的子类，所以默认情况下，编译时异常不会回滚。
    //可以为Transactional注解的rollbackFor属性指定回滚的异常类型，如：@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public void save(Emp emp) {
        //添加员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
        //添加用户经历信息
        List<EmpExpr> empExprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(empExprList))//使用springboot自带的工具CollectionUtils类判断集合是否为空
        {
            empExprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.saveBatch(empExprList);
        }
    }
    //删除员工信息，同时删除对应员工的工作经历
    //开启事务，保证删除员工信息和删除员工经历信息同时成功或失败
    @Transactional(rollbackFor = {Exception.class})//设置回滚等级为Exception
    @Override
    public void delete(List<Integer> ids) {
        log.info("根据id删除员工：{}",ids);
        //删除员工信息
        empMapper.deleteById(ids);
        //删除员工经历信息
        empExprMapper.deleteByEmpId(ids);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.修改员工信息
        //1.1设置更新时间
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        //2.更新员工工作经历信息（先删除再添加的暴力方式）
        //2.1删除员工工作经历信息
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));//使用Arrays.asList()将数组转换为集合。
        List<EmpExpr> empExprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(empExprList)) //使用springboot自带的工具CollectionUtils类判断集合是否为空，如果为空则不执行添加员工经历的操作
        {
            //为每个员工经历设置员工id
            empExprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.saveBatch(empExprList);
        }
    }

    @Override
    public JobOption getEmpJobData() {
        //对查询到的结果集进行封装，返回给前端
        List<Map<String,Object>> jobList = empMapper.countByJob();
        //使用流式编程进行封装
        List<Object> pos = jobList.stream().map(mapper -> mapper.get("pos")).toList();
        List<Object> num = jobList.stream().map(mapper -> mapper.get("num")).toList();
        return new JobOption(pos,num);
    }

    @Override
    public List<Map<String,Object>> getEmpGenderData() {
        //这里不需要进行操作，直接返回List集合
        return empMapper.countByGender();
    }

    @Override
    public List<Emp> getMasterList() {
        return empMapper.getMasterlist();
    }
    //登录操作
    @Override
    public LoginResult selectByUsernameAndPassword(Emp emp) {
        //如果有数据则封装生成令牌并封装
        LoginResult loginResult=empMapper.selectByUsernameAndPassword(emp);
        if(loginResult!=null)
        {
            Map<String,Object> claims = new HashMap<>();
            claims.put("id", loginResult.getId());
            claims.put("username", loginResult.getUsername());
            return new LoginResult(loginResult.getId(),loginResult.getUsername(),loginResult.getName(), JWTUtils.generateToken(claims));
        }
        return null;
    }
}
