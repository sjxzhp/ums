package com.sz.ums.web;

import com.sz.ums.domain.User;
import com.sz.ums.repo.UserRepo;
import com.sz.ums.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/user")
public class LoginController {
    @Autowired
    private UserRepo userRepo;
    public LoginController(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    @PostMapping("/login")
    public void login(@RequestParam String username,@RequestParam String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userRepo.findUserByUsernameAndPassword(username, password);
        if (user!=null){
            String jwt = JWTUtil.createJWT(username, password);
            HttpServletResponse httpServletResponse = JWTUtil.setCookie(jwt, response);
            httpServletResponse.sendRedirect("http://www.hello.com:8080/");
        }else{
            response.sendRedirect("http://www.hello.com:8081/");
        }

    }
}