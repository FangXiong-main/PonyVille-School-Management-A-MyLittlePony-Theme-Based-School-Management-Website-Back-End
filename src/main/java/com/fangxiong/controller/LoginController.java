package com.fangxiong.controller;

import com.fangxiong.pojo.Emp;
import com.fangxiong.pojo.LoginResult;
import com.fangxiong.pojo.Result;
import com.fangxiong.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp)
    {
        log.info("登录操作：{}",emp);
        LoginResult loginResult =empService.selectByUsernameAndPassword(emp);
        if(loginResult!=null)
        {
            return Result.success(loginResult);
        }
        return Result.error("用户名或密码错误");
    }
}
