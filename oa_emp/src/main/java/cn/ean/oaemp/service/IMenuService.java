package cn.ean.oaemp.service;

import cn.ean.oaemp.model.po.MenuPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @FileName IMenuService
 * @Author ean
 * @Date 2023/1/26 01:22
 **/
public interface IMenuService extends IService<MenuPO> {

    List<MenuPO> getMenusByUserId();

    /**
     * 根据角色获取菜单列表
     * @return 角色对应的菜单
     */
    List<MenuPO> getMenusWithRole();

    /**
     * 查询所有菜单
     * @return 所有菜单
     */
    List<MenuPO> getAllMenus();
}
