package cn.ean.oa_emp.service;

import cn.ean.oa_emp.model.bo.ResponseBO;
import cn.ean.oa_emp.model.po.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @FileName IUserService
 * @Author ean
 * @Date 2023/1/3 18:15
 **/
public interface IUserService extends IService<UserPO> {

    /**
     * 根据用户名获取用户
     * @param workId
     * @return
     */
    UserPO getUserByUserName(String workId);
}
