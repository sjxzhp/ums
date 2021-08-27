package com.sz.ums.ums.util;

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
    private static final String JWT_KEY="zhp";
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
