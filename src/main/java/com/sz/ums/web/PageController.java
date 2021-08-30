package com.sz.ums.web;

import com.sz.ums.domain.Auth;
import com.sz.ums.domain.User;
import com.sz.ums.repo.AuthRepo;
import com.sz.ums.util.JWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PageController {

    private final AuthRepo authRepo;

    public PageController(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @RequestMapping("/api/login/test")
    public String login(){
        return "login";
    }

    @GetMapping("/s/{view}")
    public String view(@PathVariable String view, Model model, HttpServletRequest request, HttpServletResponse response) {
        String jwt = request.getQueryString().substring(4);
        User user = JWTUtil.getUser(jwt);
        List<Auth> authList = authRepo.findAuthList(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("auths",authList);
        return "view/" + view;
    }

}
