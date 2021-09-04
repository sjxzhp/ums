package com.sz.ums.service;

import com.sz.ums.domain.Auth;
import com.sz.ums.repo.AuthRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final AuthRepo authRepo;

    @Value("${sysCode.value:100}")
    private String sysCode;

    public MenuService(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    public List<Auth> findMenuList(String menuName) {
        List<Auth> menuList = null;
        if (menuName != null){
            menuList = authRepo.findAllByMenuNameLikeAndSysCode("%"+menuName+"%", sysCode);
        }else {
            menuList = authRepo.findAllBySysCode(sysCode);
        }
        return menuList;
    }
}
