//package com.fangxiong.filter;
//
//import com.fangxiong.Utils.CurrentHolder;
//import com.fangxiong.Utils.JWTUtils;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//@Slf4j
//@WebFilter(urlPatterns = "/*")
//public class TokenFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        //将servletRequest和servletResponse转换为HttpServletRequest和HttpServletResponse
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String uri=request.getRequestURI(); //获取请求的uri（请求路径）
//        if(uri.contains("/login"))
//        {
//            log.info("用户进行登录操作");
//            filterChain.doFilter(request,response); //放行
//            return;
//        }
//        String token=request.getHeader("token");
//        if(token==null || token.isEmpty())
//        {
//            log.info("用户未登录");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//        try{
//            Claims claims = JWTUtils.parseToken(token);
//            CurrentHolder.setCurrentId(Integer.valueOf(claims.get("id").toString()));
//        } catch (Exception e) {
//            log.info("token无效,跳转到登录界面");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//        //校验通过，放行
//        log.info("校验通过，放行");
//        filterChain.doFilter(request,response);
//        //执行到这代表已执行完所有逻辑操作
//        CurrentHolder.remove(); //清除当前线程的id
//    }
//}
