package com.sz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.sz.ums.filter")
@SpringBootApplication
public class UmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsApplication.class, args);
    }
}


