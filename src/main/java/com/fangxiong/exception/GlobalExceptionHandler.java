package com.fangxiong.exception;

import com.fangxiong.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler //异常处理方法
    public Result handleException(Exception e) {
        log.error("出现错误:",e);
        return Result.error("服务器错误，请联系管理员");
    }
    @ExceptionHandler //用于处理数据库主键重复异常
    public Result DuplicateKeyException(DuplicateKeyException e) {
        log.error("出现错误:",e);
        String message = e.getMessage();
        int index = message.indexOf("Duplicate entry");
        String[] s = message.substring(index).split(" ");
        String column = s[5].substring(0,s[5].indexOf("\n")); //获取字段名
        String item=column;
        // 根据字段名获取错误提示信息
        item = switch (column) {
            case "'emp.username'" -> "用户名";
            case "'emp.phone'" -> "手机号";
            case "'student.no'" -> "学号";
            default -> item;
        };
        return Result.error(item+":" + s[2] + "已存在");
    }
}
