package com.fangxiong.config;

import com.fangxiong.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override  //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**") //拦截所有请求
                .excludePathPatterns("/login");//放行登录请求
    }
}
