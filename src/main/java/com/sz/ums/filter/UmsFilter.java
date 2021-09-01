package com.sz.ums.filter;
import com.sz.ums.domain.User;
import com.sz.ums.repo.UserRepo;
import com.sz.ums.util.JWTUtil;
import com.sz.ums.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "umsFilter")
@Slf4j
public class UmsFilter implements Filter {
    private String [] prefix={};
    private String [] suffix={"/login",".png",".css",".js",".jpg",".map"};
    @Autowired
    private UserRepo userRepo;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        String jwt = "";
        if(noAuth(url)&&!request.getMethod().equals(RequestMethod.OPTIONS)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for (Cookie cookie:cookies){
                        if (cookie.getName().equals("jwt")){
                            jwt=cookie.getValue();
                        }
                }
            }
            if (jwt==null||jwt==""){
                jwt=request.getParameter("jwt");
            }
            if (jwt!=null&&jwt!=""){
                if (JWTUtil.validJWT(jwt)){
                    User user=JWTUtil.getUser(userRepo,jwt);
                    UserUtil.setUserLocal(user);
                    filterChain.doFilter(servletRequest,servletResponse);
                    UserUtil.removeUserLocal();
                }
            }else{
                HttpServletResponse response=(HttpServletResponse)servletResponse;
                response.sendRedirect(url);
            }
        }

    }


    @Override
    public void destroy() {
        log.info("umsFilter销毁");
    }
    private boolean noAuth(String url){
        for (int i=0;i<prefix.length;i++){
            if (url.startsWith(prefix[i])){
                return true;
            }
        }
        for (int i=0;i<suffix.length;i++){
            if (url.endsWith(suffix[i])){
                return true;
            }
        }
        return false;
    }
}

