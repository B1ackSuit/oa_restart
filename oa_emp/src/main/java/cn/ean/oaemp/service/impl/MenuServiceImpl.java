package cn.ean.oaemp.service.impl;

import cn.ean.oaemp.mapper.MenuMapper;
import cn.ean.oaemp.model.po.MenuPO;
import cn.ean.oaemp.model.po.UserPO;
import cn.ean.oaemp.service.IMenuService;
import cn.ean.oaemp.util.UserUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName MenuServiceImpl
 * @Author ean
 * @Date 2023/1/26 01:23
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuPO> implements IMenuService {

    private MenuMapper menuMapper;

    private RedisTemplate redisTemplate;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper,
                           RedisTemplate<String, Object> redisTemplate) {
        this.menuMapper = menuMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    @Override
    public List<MenuPO> getMenusByUserId() {
        Integer userId = UserUtil.getCurrentUser().getPkId();

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        List<MenuPO> menus = (List<MenuPO>) valueOperations.get("oaemp_menu_" + userId);

        if (!(null == menus || menus.size() == 0)) {
            menus = menuMapper.getMenusByUserId(userId);
            valueOperations.set("oaemp_menu_" + userId, menus);
        }

        return menus;
    }

    /**
     * 根据角色获取菜单列表
     *
     * @return 菜单
     */
    @Override
    public List<MenuPO> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }
}
