package com.fangxiong;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {
    @Test
    public void JWT_Test() {
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        //生成令牌
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"ZmFuZ3hpb25n") //指定签名算法和密钥
                .addClaims(dataMap) //添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis()+60*1000)) //设置过期时间(使用毫秒)
                .compact(); //生成令牌
        System.out.println(jwt);
    }

    @Test
    public void JWT_Test_Parse() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0NTAzNDEyM30.YiQPJhk4hAD73hqZ_R5jzzM6Ft6A-GXA2vVD6MFjIZE";
        Claims claims=Jwts.parser()
                .setSigningKey("ZmFuZ3hpb25n") //设置密钥（需要与生成令牌设置的秘钥相同）
                .parseClaimsJws(jwt) //解析令牌
                .getBody(); //获取数据
        System.out.println(claims); //只要令牌解析报错就代表这个令牌失效
    }
}
