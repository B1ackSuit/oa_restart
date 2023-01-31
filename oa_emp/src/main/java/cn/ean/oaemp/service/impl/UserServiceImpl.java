package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.RoleMapper;
import cn.ean.oaemp.mapper.UserMapper;
import cn.ean.oaemp.mapper.UserRoleMapper;
import cn.ean.oaemp.model.bo.ResponseBO;
import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.model.po.UserPO;
import cn.ean.oaemp.model.po.UserRolePO;
import cn.ean.oaemp.service.IUserService;
import cn.ean.oaemp.util.UserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @FileName UserServiceImpl
 * @Author ean
 * @Date 2023/1/3 18:18
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

    Logger userServiceImplLogger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;

    private RoleMapper roleMapper;

    private UserRoleMapper userRoleMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           RoleMapper roleMapper,
                           UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
    }


    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public UserPO getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<UserPO>()
                .eq("uk_workid", username)
                .eq("is_enabled", true));
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<RolePO> getRoles(Integer userId) {
        return roleMapper.getRoles(userId);
    }

    /**
     * 获取所有用户
     *
     * @param keyWords
     * @return 用户
     */
    @Override
    public List<UserPO> getAllUsers(String keyWords) {

        return userMapper.getAllUsers(UserUtil.getCurrentUser().getPkId(), keyWords);
    }

    /**
     * 更新用户角色
     *
     * @param userId
     * @param rids
     * @return ResponseBO
     */
    @Override
    @Transactional
    public ResponseBO updateUserRole(Integer userId, Integer[] rids) {
        userRoleMapper.delete(new QueryWrapper<UserRolePO>().eq("user_id", userId));
        Integer result = userRoleMapper.updateUserRole(userId, rids);
        if (rids.length == result) {
            return ResponseBO.success("用户角色更新成功");
        }
        return ResponseBO.error("用户角色更新失败");
    }
}
