package cn.ean.oa_emp.service;

import cn.ean.oa_emp.model.bo.ResponseBO;
import cn.ean.oa_emp.model.po.UserPO;
import cn.ean.oa_emp.model.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

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
    ResponseBO login(String username, String password, HttpServletRequest request);


    ResponseBO signup(UserLoginVO userLoginVO);
}
