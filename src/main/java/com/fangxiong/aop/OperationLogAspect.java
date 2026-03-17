package com.fangxiong.aop;

import com.fangxiong.Utils.CurrentHolder;
import com.fangxiong.Utils.JWTUtils;
import com.fangxiong.mapper.OperateLogMapper;
import com.fangxiong.pojo.LoginResult;
import com.fangxiong.pojo.OperateLog;
import io.jsonwebtoken.Claims;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component // 将切面类交给Spring容器管理
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.fangxiong.anno.Log)")
    public Object LogOperation(ProceedingJoinPoint pjp) throws Throwable {
        long startTime=System.currentTimeMillis();
        //执行目标方法
        Object result=pjp.proceed();
        long endTime=System.currentTimeMillis();
        long costTime=endTime-startTime; // 执行时长
        OperateLog operateLog=new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(pjp.getTarget().getClass().getName());
        operateLog.setMethodName(pjp.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString():"void");
        operateLog.setCostTime(costTime);
        operateLogMapper.insert(operateLog);
        return result;
    }
    private Integer getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }
}
