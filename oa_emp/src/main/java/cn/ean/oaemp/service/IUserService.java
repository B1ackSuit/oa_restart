package cn.ean.oaemp.service;

import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.model.po.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @FileName IUserService
 * @Author ean
 * @Date 2023/1/3 18:15
 **/
public interface IUserService extends IService<UserPO> {

    /**
     * 根据用户名获取用户
     * @param workId
     * @return 用户的用户名
     */
    UserPO getUserByUserName(String workId);

    /**
     * 根据用户id查询角色列表
     *
     * @param userId
     * @return 用户的角色列表
     */
    List<RolePO> getRoles(Integer userId);

    /**
     * 获取所有用户
     * @param keyWords
     * @return 用户
     */
    List<UserPO> getAllUsers(String keyWords);

    /**
     * 更新用户角色
     * @param userId
     * @param rids
     * @return ResponseBO
     */
    ResponseBO updateUserRole(Integer userId, Integer[] rids);
}
