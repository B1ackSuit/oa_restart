package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.MenuRoleMapper;
import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.MenuRolePO;
import cn.ean.oaemp.service.IMenuRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * @FileName MenuRoleServiceImpl
 * @Author ean
 * @Date 2023/1/28 00:10
 **/
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRolePO> implements IMenuRoleService {

    private MenuRoleMapper menuRoleMapper;

    @Autowired
    public MenuRoleServiceImpl(MenuRoleMapper menuRoleMapper) {
        this.menuRoleMapper = menuRoleMapper;
    }

    /**
     * 更新角色菜单
     *
     * @param roleId
     * @param menuIds
     * @return ResponseBO
     */
    @Override
    @Transactional
    public ResponseBO updateMenuRole(Integer roleId, Integer[] menuIds) {
        menuRoleMapper.delete(new QueryWrapper<MenuRolePO>().eq("role_id", roleId));
        if (null == menuIds || menuIds.length == 0) {
            return ResponseBO.success("角色菜单信息更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(roleId, menuIds);
        if (menuIds.length == result) {
            return ResponseBO.success("角色菜单信息更新成功");
        }

        return ResponseBO.success("角色菜单信息更新失败");
    }
}
