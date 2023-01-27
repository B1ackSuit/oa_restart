package cn.ean.oaemp.service;

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
     * @return
     */
    UserPO getUserByUserName(String workId);

    /**
     * 根据用户id查询角色列表
     *
     * @param userId
     * @return
     */
    List<RolePO> getRoles(Integer userId);
}
