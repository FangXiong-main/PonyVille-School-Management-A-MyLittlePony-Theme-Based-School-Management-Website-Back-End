package com.fangxiong.interceptor;

import com.fangxiong.Utils.CurrentHolder;
import com.fangxiong.Utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component // 将类标记为组件，自动注入到spring容器中
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //业务逻辑与Filter类似，直接复制修改即可（return true代表放行，false表示不放行）
//        String uri=request.getRequestURI(); //获取请求的uri（请求路径）
//        if(uri.contains("/login"))
//        {
//            log.info("用户进行登录操作");
//            return true; //放行
//        } //已经在config中处理放行/login，无需再处理
        String token=request.getHeader("token");
        Integer id = request.getIntHeader("id");
        if(token==null || token.isEmpty())
        {
            log.info("用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        try{
            JWTUtils.parseToken(token);
        } catch (Exception e) {
            log.info("token无效,跳转到登录界面");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //校验通过，放行
        CurrentHolder.setCurrentId(id);
        log.info("校验通过，放行");
        return true;
    }

}
