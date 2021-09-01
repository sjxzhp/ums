package com.sz.ums.web;

import com.sz.ums.domain.User;
import com.sz.ums.repo.UserRepo;
import com.sz.ums.service.LoginService;
import com.sz.ums.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/user")
public class LoginController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LoginService loginService;
    public LoginController(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    @PostMapping("/login")
    public void login(@RequestParam String username,@RequestParam String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userRepo.findUserByUsernameAndPassword(username, password);
        if (user!=null){
            String jwt = JWTUtil.createJWT(username, password);
            JWTUtil.setCookie(jwt, response);
            response.setHeader("jwt",jwt);
            response.sendRedirect("/ums/s/main-home");
        }else{
            response.sendRedirect("http://www.hello.com:8081/");
        }
    }
    @PostMapping("/checkUser")
    @ResponseBody
    public boolean checkUser(String username,String password,HttpServletResponse response) throws IOException {
        if (loginService.checkUser(username,password)){
            response.sendRedirect("http://localhost:8900/ums/api/login/tomain"); 
        }
        return false;
    }
}
