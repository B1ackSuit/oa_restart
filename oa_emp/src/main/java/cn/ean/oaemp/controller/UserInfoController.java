package cn.ean.oaemp.controller;

import cn.ean.oaemp.model.po.UserPO;
import cn.ean.oaemp.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @FileName UserInfoController
 * @Author ean
 * @Date 2023/1/4 17:21
 **/
@RestController
public class UserInfoController {
    private IUserService userService;

    @Autowired
    public UserInfoController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/user/info")
    public UserPO getUserInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String name = principal.getName();
        UserPO user = userService.getUserByUserName(name);
        // 前端不需要用户密码，设置为null作为保护
        user.setPassword(null);
        return user;
    }

}
