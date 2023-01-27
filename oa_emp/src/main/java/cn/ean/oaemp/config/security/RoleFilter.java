package cn.ean.oaemp.config.security;

import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.RolePO;
import cn.ean.oaemp.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求url分析请求所需的角色
 *
 * @FileName RoleFilter
 * @Author ean
 * @Date 2023/1/26 21:27
 **/
@Component
public class RoleFilter implements FilterInvocationSecurityMetadataSource {

    private IMenuService menuService;

    @Autowired
    public RoleFilter(IMenuService menuService) {
        this.menuService = menuService;
    }

    public RoleFilter() {
    }

    AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an
     * empty collection if there are no applicable attributes.
     * @throws IllegalArgumentException if the passed object is not of a type supported by
     *                                  the <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<MenuPO> menus = menuService.getMenusWithRole();
        for (MenuPO menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                String[] str = menu.getRoles().stream().map(RolePO::getAuthority).toArray(String[]::new);
                return org.springframework.security.access.SecurityConfig.createList(str);
            }
        }

        return org.springframework.security.access.SecurityConfig.createList("ROLE_LOGIN");
    }

    /**
     * If available, returns all of the {@code ConfigAttribute}s defined by the
     * implementing class.
     * <p>
     * This is used by the {@link AbstractSecurityInterceptor} to perform startup time
     * validation of each {@code ConfigAttribute} configured against it.
     *
     * @return the {@code ConfigAttribute}s or {@code null} if unsupported
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to
     * provide {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
