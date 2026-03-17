package com.fangxiong.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String SECRET_KEY = "ZmFuZ3hpb25n"; // 密钥
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000; // 12小时过期时间

    /**
     * 生成JWT令牌
     * @param claims 自定义数据
     * @return 生成的JWT令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定签名算法和密钥
                .compact(); // 生成令牌
    }

    /**
     * 解析JWT令牌
     * @param token JWT令牌
     * @return 解析后的数据
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 设置密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取数据
    }

}
