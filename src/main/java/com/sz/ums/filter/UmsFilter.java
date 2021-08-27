package com.sz.ums.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "umsFilter")
@Slf4j
public class UmsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        if (url.indexOf("/dop")>-1) {
            log.info("umsFilter接收到一个请求,url为" + url + ",认证失败");
            ((HttpServletResponse) servletResponse).sendRedirect("http://www.hello.com:8080/");
        } else {
            log.info("umsFilter接收到一个请求,url为" + url + ",校验通过");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {
        log.info("umsFilter销毁");
    }
}

