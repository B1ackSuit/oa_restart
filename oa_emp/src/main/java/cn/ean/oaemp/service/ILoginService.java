package cn.ean.oaemp.service;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.vo.UserLoginVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @FileName ILoginService
 * @Author ean
 * @Date 2023/1/4 17:13
 **/
public interface ILoginService{

    /**
     * 登录返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return ResponseBO
     */
    ResponseBO login(String username, String password, String code, HttpServletRequest request);


    ResponseBO signup(UserLoginVO userLoginVO);
}
