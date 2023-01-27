package cn.ean.oaemp.config.security;

import cn.ean.oaemp.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取头-->根据头获取token-->根据token 获取username-->判断用户是否登录-->执行登录-->设置登录信息-->过滤器放行
 *
 * @FileName JwtAuthenticationTokenFilter
 * @Author ean
 * @Date 2023/1/4 16:51
 **/

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(tokenHeader);

        if (null != authHeader && authHeader.startsWith(tokenHead)) {

            String authToken = authHeader.substring(tokenHead.length());
            String userName = jwtTokenUtils.getUserNameFromToken(authToken);


            if (null != userName && null == SecurityContextHolder.getContext().getAuthentication()) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtTokenUtils.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }



            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
