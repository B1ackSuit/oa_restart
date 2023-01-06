package cn.ean.oa_emp.service.impl;

import cn.ean.oa_emp.mapper.UserMapper;
import cn.ean.oa_emp.model.bo.ResponseBO;
import cn.ean.oa_emp.model.po.UserPO;
import cn.ean.oa_emp.model.vo.UserLoginVO;
import cn.ean.oa_emp.service.ILoginService;
import cn.ean.oa_emp.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @FileName LoginServiceImpl
 * @Author ean
 * @Date 2023/1/4 17:14
 **/
@Service
public class LoginServiceImpl implements ILoginService {

    Logger loginServiceImplLogger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    private JwtTokenUtil jwtTokenUtil;

    private UserMapper userMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public LoginServiceImpl(UserDetailsService userDetailsService,
                            PasswordEncoder passwordEncoder,
                            JwtTokenUtil jwtTokenUtil,
                            UserMapper userMapper) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    /**
     * 登录返回token
     *
     * @param username
     * @param password
     * @param request
     * @return ResponseBO
     */
    @Override
    public ResponseBO login(String username, String password, HttpServletRequest request) {
        loginServiceImplLogger.debug("login UserLoginVO info ==> " +
                "username:" + username + ", password: " + password + ", code: ");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return ResponseBO.error("用户名或密码不正确");
        }

        if (!userDetails.isEnabled()) {
            return ResponseBO.error("账号被禁用，请联系管理员");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();

        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        System.out.println("tokenHead: " + tokenHead);
        loginServiceImplLogger.debug("username: " + username + " Token: " + token + ", TokenHead: " + tokenHead);

        return ResponseBO.success("登录成功", tokenMap);
    }

    @Override
    public ResponseBO signup(UserLoginVO userLoginVO) {
        String encode = passwordEncoder.encode(userLoginVO.getPassword());
        loginServiceImplLogger.debug("encode: " + encode);
        UserPO userPO = new UserPO();
        userPO.setWorkId(Integer.valueOf(userLoginVO.getUsername()));
        userPO.setPassword(encode);
        int insert = userMapper.insert(userPO);
        if (insert != -1) {
            return ResponseBO.success("注册成功");
        } else {
            return ResponseBO.error("注册失败");
        }

    }
}
