package cn.ean.oaemp.controller;


import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.model.po.UserPO;
import cn.ean.oaemp.service.IRoleService;
import cn.ean.oaemp.service.IUserService;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @FileName UserController
 * @Author ean
 * @Date 2023/1/28 15:55
 **/
@RestController
@RequestMapping("/system/user")
public class UserController {


    private IUserService userService;

    private IRoleService roleService;

    public UserController() {
    }

    @Autowired
    public UserController(IUserService adminService,
                           IRoleService roleService) {
        this.userService = adminService;
        this.roleService = roleService;
    }

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/")
    public List<UserPO> getAllUsers(String keyWords){
        return userService.getAllUsers(keyWords);
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/")
    public ResponseBO updateUser(@RequestBody UserPO userPO) {
        if (userService.updateById(userPO)) {
            return ResponseBO.success("用户更新成功");
        }
        return ResponseBO.error("用户更新失败");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public ResponseBO deleteUser(@PathVariable Integer id) {
        if (userService.removeById(id)) {
            return ResponseBO.success("用户删除成功");
        }
        return ResponseBO.error("用户删除失败");

    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<RolePO> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "更新用户角色")
    @PutMapping("/role")
    public ResponseBO updateUserRole(Integer userId, Integer[] rids) {
        return userService.updateUserRole(userId, rids);
    }

}
