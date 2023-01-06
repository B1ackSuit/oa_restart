package cn.ean.oa_emp.controller;

import cn.ean.oa_emp.model.bo.ResponseBO;
import cn.ean.oa_emp.model.po.UserPO;
import cn.ean.oa_emp.model.vo.UserLoginVO;
import cn.ean.oa_emp.service.ILoginService;
import cn.ean.oa_emp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @FileName LoginController
 * @Author ean
 * @Date 2023/1/3 18:09
 **/
@Api(tags = "LoginController")
@RestController
public class LoginController {

    private ILoginService loginService;


    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public ResponseBO login(@RequestBody UserLoginVO userLoginVO, HttpServletRequest request) {
        return loginService.login(userLoginVO.getUsername(), userLoginVO.getPassword(), request);
    }

    @ApiOperation(value = "员工注册：后续设置为添加员工后自动注册，关闭此接口")
    @PostMapping("/signup")
    public ResponseBO signup(@RequestBody UserLoginVO userLoginVO) {
        return loginService.signup(userLoginVO);
        //return ResponseBO.success("注册成功");
    }

    /**
     * 注销操作在前端完成，后端若有日志需要只需要返回成功并记录日志即可
     * @return success
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseBO logout() {
        return ResponseBO.success("注销成功");
    }
}
