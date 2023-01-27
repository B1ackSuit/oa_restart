package cn.ean.oaemp.service;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.MenuRolePO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @FileName MenuRoleService
 * @Author ean
 * @Date 2023/1/28 00:09
 **/
public interface IMenuRoleService extends IService<MenuRolePO> {

    /**
     * 更新角色菜单
     * @param roleId
     * @param menuIds
     * @return ResponseBO
     */
    ResponseBO updateMenuRole(Integer roleId, Integer[] menuIds);
}
