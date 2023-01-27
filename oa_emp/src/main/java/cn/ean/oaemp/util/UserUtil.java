package cn.ean.oaemp.util;

import cn.ean.oaemp.model.po.UserPO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @FileName UserUtil
 * @Author ean
 * @Date 2023/1/26 14:11
 **/
public class UserUtil {

    public static UserPO getCurrentUser() {
        return (UserPO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
