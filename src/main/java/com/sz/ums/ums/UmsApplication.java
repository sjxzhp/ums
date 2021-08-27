package com.sz.ums.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.sz.ums.filter")
public class UmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.sz.ums.UmsApplication.class, args);
    }

}
