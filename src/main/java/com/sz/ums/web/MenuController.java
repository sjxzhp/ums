package com.sz.ums.web;

import com.sz.ums.domain.Auth;
import com.sz.ums.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 通过菜单名称和项目代码查询对应的菜单
     * @param menuName
     * @return
     */
    @PostMapping("/findMenuList")
    public List<Auth> findMenuList(String menuName){
        return menuService.findMenuList(menuName);
    }
}
