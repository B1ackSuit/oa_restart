package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.UserMapper;
import cn.ean.oaemp.model.po.UserPO;
import cn.ean.oaemp.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName UserServiceImpl
 * @Author ean
 * @Date 2023/1/3 18:18
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {
    Logger userServiceImplLogger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
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
                .eq("enabled", true));
    }


}
