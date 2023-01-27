package cn.ean.oaemp.controller;

import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.service.IMenuService;
import cn.ean.oaemp.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @FileName MenuController
 * @Author ean
 * @Date 2023/1/22 12:51
 **/
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    private IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<MenuPO> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }
}
