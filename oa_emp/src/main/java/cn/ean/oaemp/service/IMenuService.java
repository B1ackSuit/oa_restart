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
     * @return 菜单
     */
    List<MenuPO> getMenusWithRole();

}
