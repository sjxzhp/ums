package com.sz.ums.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

public class JWTUtil {
    private static final long JWT_WEB_TTL=30*60*1000;
    private static final String JWT_KEY="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDdzlFTZgQ0I/SwjF3SYFT7lk9dVQqlQ+EGmIX5XcvtBS7Avby0zC5jPYEzsA15NIDn/SUasOiFGP43lYnZQ/gQ8YRyURlQqmWbgTx50IR7bp4O+z1ijLhNzl/V/uHbrMWiIoifrvuYVlW0nXvFszBJO7V2lnBICjaSQdTRBE8EHItPtbpEzjUTJFkGK9Ki104liClrtSX2VzYB5yl1aak8+cmowJY+6RAcs26TaL7tyFx3e2EXm2adAwLtL2pV1F5qwpV7LQpTMFDhyplhZegPz3CYMbebJlPFeWrr/5lbAdgccmgtyRrGc0JvE2yHI52iPhlQOecJvifg5J2foOJbTZy/EI35xJ1YgLsdoVxzFQxIgKhaM6Cgr31+zdyjjmy8s7mGTGZQRBKUwoagT9gP/O3b4CT5oZQcS/Sh5hgd062q2lZ3U94WKF5ZcThylIZhRz7HrmohtN/vl4QZ3yOpwFRtrv5RCtiwGbwaX/HsZ6j60+6GAlJWDY1irumu7n8= 1043495113@qq.com";
    //创建JWT
    public static String createJWT(String username,String password){
        long nowMillis=System.currentTimeMillis();
        Date now=new Date(nowMillis);
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("password",password);
        JwtBuilder jwtBuilder= Jwts.builder()
                //数据
                .setClaims(jsonObject)
                //唯一标识符
                .setId(UUID.randomUUID().toString())
                //签发时间
                .setIssuedAt(now)
                //签发人
                .setSubject("sjx")
                //签名算法和密钥
                .signWith(signatureAlgorithm,JWT_KEY);
        long expMills=nowMillis+JWT_WEB_TTL;
        Date exp=new Date(expMills);
        jwtBuilder.setExpiration(exp);
        return jwtBuilder.compact();
    }
    //解析JWT
    public static Claims parseJWT(String jwt){
        if (jwt==null||jwt==""){
            return null;
        }
        return Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJws(jwt).getBody();
    }
    //验证JWT
    public static boolean checkJWT(String jwt,String username){
        Claims claims=parseJWT(jwt);
        if (claims.get("username").equals(username)){
            return true;
        }
        return false;
    }
    public static HttpServletResponse setCookie(String jwt,HttpServletResponse response){
        Cookie cookie=new Cookie("jwt",jwt);
        cookie.setPath("/");
        cookie.setDomain("hello.com");
        response.addCookie(cookie);
        return response;
    }
}
