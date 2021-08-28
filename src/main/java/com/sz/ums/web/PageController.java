package com.sz.ums.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @RequestMapping("/api/login/test")
    public String login(){
        return "login";
    }

    @GetMapping("/s/{view}")
    public String view(@PathVariable String view) {
        return "view/" + view;
    }

}
