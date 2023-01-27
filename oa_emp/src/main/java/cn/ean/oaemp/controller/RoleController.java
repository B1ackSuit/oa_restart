package cn.ean.oaemp.controller;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.MenuRolePO;
import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.service.IMenuRoleService;
import cn.ean.oaemp.service.IMenuService;
import cn.ean.oaemp.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName RoleController
 * @Author ean
 * @Date 2023/1/27 23:38
 **/
@RestController
@RequestMapping("/system/basic/permission")
public class RoleController {

    public static final String START_WITH_PERMISSION = "ROLE_";

    private IRoleService roleService;

    private IMenuService menuService;

    private IMenuRoleService menuRoleService;

    @Autowired
    public RoleController(IRoleService roleService,
                          IMenuService menuService,
                          IMenuRoleService menuRoleService) {
        this.roleService = roleService;
        this.menuService = menuService;
        this.menuRoleService = menuRoleService;
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/role")
    public List<RolePO> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "添加角色信息")
    @PostMapping("/role")
    public ResponseBO addRole(@RequestBody RolePO role) {
        if (!role.getAuthority().startsWith(START_WITH_PERMISSION)) {
            role.setAuthority(START_WITH_PERMISSION + role.getAuthority());
        }
        if (roleService.save(role)) {
            return ResponseBO.success("角色信息添加成功");
        }
        return ResponseBO.error("角色信息添加失败");
    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/role/{rid}")
    public ResponseBO deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return ResponseBO.success("角色信息删除成功");
        }

        return ResponseBO.error("角色信息删除失败");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<MenuPO> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{roleId}")
    public List<Integer> getMenuIdByRoleId(@PathVariable Integer roleId) {
        // 将取到的List<MenuRole>通过stream流转成mid
        List<MenuRolePO> result = menuRoleService.list(
                new QueryWrapper<MenuRolePO>().eq("role_id", roleId));
        return result
            .stream()
            .map(MenuRolePO::getMenuId)
            .collect(Collectors.toList());
    }

    /**
     *
     * @param roleId 角色id
     * @param menuIds 角色菜单数组
     * @return ResponseBO
     */
    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public ResponseBO updateMenuRole(Integer roleId, Integer[] menuIds) {
        return menuRoleService.updateMenuRole(roleId, menuIds);
    }
}
