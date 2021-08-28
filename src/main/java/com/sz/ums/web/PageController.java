package com.sz.ums.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/login")
public class PageController {
    @RequestMapping("/test")
    public String login(){
        return "login";
    }

}
