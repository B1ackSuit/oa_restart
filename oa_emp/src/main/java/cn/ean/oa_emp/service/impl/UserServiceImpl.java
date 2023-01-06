package cn.ean.oa_emp.service.impl;

import cn.ean.oa_emp.mapper.UserMapper;
import cn.ean.oa_emp.model.bo.ResponseBO;
import cn.ean.oa_emp.model.po.UserPO;
import cn.ean.oa_emp.service.IUserService;
import cn.ean.oa_emp.util.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
